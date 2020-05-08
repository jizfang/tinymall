package com.example.tinymall.controller.wx;

import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.common.result.CommonResult;
import com.example.tinymall.core.system.SystemConfig;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.core.validator.Order;
import com.example.tinymall.core.validator.Sort;
import com.example.tinymall.entity.*;
import com.example.tinymall.model.bo.LoginUser;
import com.example.tinymall.service.*;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @ClassName WxGoodsController
 * @Description
 * @Author jzf
 * @Date 2020-4-11 17:34
 */
@RestController
@RequestMapping("/wx/goods")
public class WxGoodsController {

    @Autowired
    private TinymallGoodsService goodsService;
    @Autowired
    private TinymallCategoryService categoryService;
    @Autowired
    private TinymallSearchHistoryService searchHistoryService;
    @Autowired
    private TinymallGoodsAttributeService goodsAttributeService;
    @Autowired
    private TinymallGoodsSpecificationService goodsSpecificationService;
    @Autowired
    private TinymallGoodsProductService productService;
    @Autowired
    private TinymallIssueService goodsIssueService;
    @Autowired
    private TinymallBrandService brandService;
    @Autowired
    private TinymallCommentService commentService;
    @Autowired
    private TinymallUserService userService;
    @Autowired
    private TinymallGrouponRulesService rulesService;
    @Autowired
    private TinymallCollectService collectService;
    @Autowired
    private TinymallFootprintService footprintService;

    private final static ArrayBlockingQueue<Runnable> WORK_QUEUE = new ArrayBlockingQueue<>(9);

    private final static RejectedExecutionHandler HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();
    private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(16, 16, 1000, TimeUnit.MILLISECONDS, WORK_QUEUE, HANDLER);
    /**
     * 商品详情
     * <p>
     * 用户可以不登录。
     * 如果用户登录，则记录用户足迹以及返回用户收藏信息。
     *
     * @param id     商品ID
     * @return 商品详情
     */
    @GetMapping("detail")
    public Object detail( @NotNull Integer id) {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        AssertUtils.notNull(loginUser,"用户未登录");
        Integer userId = loginUser.getId();

        // 商品信息
        TinymallGoods info = goodsService.findById(id);

        // 商品属性
        Callable<List> goodsAttributeListCallable = () -> goodsAttributeService.queryByGid(id);

        // 商品规格 返回的是定制的GoodsSpecificationVo
        Callable<Object> objectCallable = () -> goodsSpecificationService.getSpecificationVoList(id);

        // 商品规格对应的数量和价格
        Callable<List> productListCallable = () -> productService.queryByGid(id);

        // 商品问题，这里是一些通用问题
        Callable<List> issueCallable = () -> goodsIssueService.querySelective("", 1, 4, "", "");

        // 商品品牌商
        Callable<TinymallBrand> brandCallable = ()->{
            Integer brandId = info.getBrandId();
            TinymallBrand brand;
            if (brandId == 0) {
                brand = new TinymallBrand();
            } else {
                brand = brandService.findById(info.getBrandId());
            }
            return brand;
        };

        // 评论
        Callable<Map> commentsCallable = () -> {
            List<TinymallComment> comments = commentService.queryGoodsByGid(id, 0, 2);
            List<Map<String, Object>> commentsVo = new ArrayList<>(comments.size());
            long commentCount = PageInfo.of(comments).getTotal();
            for (TinymallComment comment : comments) {
                Map<String, Object> c = new HashMap<>();
                c.put("id", comment.getId());
                c.put("addTime", comment.getAddTime());
                c.put("content", comment.getContent());
                c.put("adminContent", comment.getAdminContent());
                TinymallUser user = userService.findById(comment.getUserId());
                c.put("nickname", user == null ? "" : user.getNickname());
                c.put("avatar", user == null ? "" : user.getAvatar());
                //c.put("picList", comment.getPicUrls());
                commentsVo.add(c);
            }
            Map<String, Object> commentList = new HashMap<>();
            commentList.put("count", commentCount);
            commentList.put("data", commentsVo);
            return commentList;
        };

        //团购信息
        Callable<List> grouponRulesCallable = () ->rulesService.queryByGoodsId(id);

        // 用户收藏
        int userHasCollect = 0;
        if (userId != null) {
            userHasCollect = collectService.count(userId, id);
        }

        // 记录用户的足迹 异步处理
        if (userId != null) {
            executorService.execute(()->{
                TinymallFootprint footprint = new TinymallFootprint();
                footprint.setUserId(userId);
                footprint.setGoodsId(id);
                footprintService.add(footprint);
            });
        }
        FutureTask<List> goodsAttributeListTask = new FutureTask<>(goodsAttributeListCallable);
        FutureTask<Object> objectCallableTask = new FutureTask<>(objectCallable);
        FutureTask<List> productListCallableTask = new FutureTask<>(productListCallable);
        FutureTask<List> issueCallableTask = new FutureTask<>(issueCallable);
        FutureTask<Map> commentsCallableTsk = new FutureTask<>(commentsCallable);
        FutureTask<TinymallBrand> brandCallableTask = new FutureTask<>(brandCallable);
        FutureTask<List> grouponRulesCallableTask = new FutureTask<>(grouponRulesCallable);

        executorService.submit(goodsAttributeListTask);
        executorService.submit(objectCallableTask);
        executorService.submit(productListCallableTask);
        executorService.submit(issueCallableTask);
        executorService.submit(commentsCallableTsk);
        executorService.submit(brandCallableTask);
        executorService.submit(grouponRulesCallableTask);

        Map<String, Object> data = new HashMap<>();

        try {
            data.put("info", info);
            data.put("userHasCollect", userHasCollect);
            data.put("issue", issueCallableTask.get());
            data.put("comment", commentsCallableTsk.get());
            data.put("specificationList", objectCallableTask.get());
            data.put("productList", productListCallableTask.get());
            data.put("attribute", goodsAttributeListTask.get());
            data.put("brand", brandCallableTask.get());
            data.put("groupon", grouponRulesCallableTask.get());
            //SystemConfig.isAutoCreateShareImage()
            data.put("share", SystemConfig.isAutoCreateShareImage());

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //商品分享图片地址
        data.put("shareImage", info.getShareUrl());
        return data;
    }

    /**
     * 根据条件搜素商品
     * <p>
     * 1. 这里的前五个参数都是可选的，甚至都是空
     * 2. 用户是可选登录，如果登录，则记录用户的搜索关键字
     *
     * @param categoryId 分类类目ID，可选
     * @param brandId    品牌商ID，可选
     * @param keyword    关键字，可选
     * @param isNew      是否新品，可选
     * @param isHot      是否热买，可选
     * @param page       分页页数
     * @param limit       分页大小
     * @param sort       排序方式，支持"add_time", "retail_price"或"name"
     * @param order      排序类型，顺序或者降序
     * @return 根据条件搜素的商品详情
     */
    @GetMapping("list")
    public Object list(
            Integer categoryId,
            Integer brandId,
            String keyword,
            Boolean isNew,
            Boolean isHot,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @Sort(accepts = {"add_time", "retail_price", "name"}) @RequestParam(defaultValue = "add_time") String sort,
            @Order @RequestParam(defaultValue = "desc") String order) {

        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        AssertUtils.notNull(loginUser,"用户未登录");
        Integer userId = loginUser.getId();
        //添加到搜索历史
        if (userId != null && !StringUtils.isNullOrEmpty(keyword)) {
            TinymallSearchHistory searchHistoryVo = new TinymallSearchHistory();
            searchHistoryVo.setKeyword(keyword);
            searchHistoryVo.setUserId(userId);
            searchHistoryVo.setFrom("wx");
            searchHistoryService.save(searchHistoryVo);
        }

        //查询列表数据
        List<TinymallGoods> goodsList = goodsService.querySelective(categoryId, brandId, keyword, isHot, isNew, page, limit, sort, order);

        // 查询商品所属类目列表。
        List<Integer> goodsCatIds = goodsService.getCatIds(brandId, keyword, isHot, isNew);
        List<TinymallCategory> categoryList = null;
        if (goodsCatIds.size() != 0) {
            categoryList = categoryService.queryL2ByIds(goodsCatIds);
        } else {
            categoryList = new ArrayList<>(0);
        }

        PageInfo<TinymallGoods> pagedList = PageInfo.of(goodsList);

        Map<String, Object> entity = new HashMap<>();
        entity.put("list", goodsList);
        entity.put("total", pagedList.getTotal());
        entity.put("page", pagedList.getPageNum());
        entity.put("limit", pagedList.getPageSize());
        entity.put("pages", pagedList.getPages());
        entity.put("filterCategoryList", categoryList);

        // 因为这里需要返回额外的filterCategoryList参数，因此不能方便使用ResponseUtil.okList
        return entity;
    }

    /**
     * 商品分类类目
     *
     * @param id 分类类目ID
     * @return 商品分类类目
     */
    @GetMapping("category")
    public Object category(@NotNull Integer id) {
        TinymallCategory cur = categoryService.findById(id);
        TinymallCategory parent = null;
        List<TinymallCategory> children = null;

        if (cur.getPid() == 0) {
            parent = cur;
            children = categoryService.queryByPid(cur.getId());
            cur = children.size() > 0 ? children.get(0) : cur;
        } else {
            parent = categoryService.findById(cur.getPid());
            children = categoryService.queryByPid(cur.getPid());
        }
        Map<String, Object> data = new HashMap<>();
        data.put("currentCategory", cur);
        data.put("parentCategory", parent);
        data.put("brotherCategory", children);
        return data;
    }

    /**
     * 在售的商品总数
     *
     * @return 在售的商品总数
     */
    @GetMapping("count")
    public Object count() {
        Integer goodsCount = goodsService.queryOnSale();
        return CommonResult.success(goodsCount);
    }

    /**
     * 商品详情页面“大家都在看”推荐商品
     *
     * @param id, 商品ID
     * @return 商品详情页面推荐商品
     */
    @GetMapping("related")
    public Object related(@NotNull Integer id) {
        TinymallGoods goods = goodsService.findById(id);
        /*if (goods == null) {
            return ResponseUtil.badArgumentValue();
        }*/

        // 目前的商品推荐算法仅仅是推荐同类目的其他商品
        int cid = goods.getCategoryId();

        // 查找六个相关商品
        int related = 6;
        List<TinymallGoods> goodsList = goodsService.queryByCategory(cid, 0, related);
        return goodsList;
    }
}

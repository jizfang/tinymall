package com.example.tinymall.controller.wx;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.core.system.SystemConfig;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.entity.TinymallCategory;
import com.example.tinymall.entity.TinymallCoupon;
import com.example.tinymall.entity.TinymallGoods;
import com.example.tinymall.model.bo.LoginUser;
import com.example.tinymall.service.*;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @ClassName WxHomeController
 * @Description
 * @Author jzf
 * @Date 2020-4-10 16:00
 */
@ResponseResult
@RestController
@RequestMapping("/wx/home")
@Slf4j
public class WxHomeController {

    @Autowired
    private TinymallAdService adService;

    @Autowired
    private TinymallGoodsService goodsService;

    @Autowired
    private TinymallBrandService brandService;

    @Autowired
    private TinymallTopicService topicService;

    @Autowired
    private TinymallCategoryService categoryService;

    @Autowired
    private WxGrouponRuleService grouponService;

    @Autowired
    private TinymallCouponService couponService;

    /**
     * 首页数据
     * @return 首页数据
     */
    @GetMapping("/index")
    public Object index() {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        AssertUtils.notNull(loginUser,"用户未登录");
        Integer userId = loginUser.getId();

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("home-pool-%d").build();

        //Common Thread Pool
        ExecutorService executorService = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        Callable<List> bannerListCallable = () -> adService.selectAll();

        Callable<List> channelListCallable = () -> categoryService.queryChannel();

        Callable<List> couponListCallable = () -> couponService.selectAll();

        Callable<List> newGoodsListCallable = () -> goodsService.queryByNew(0, SystemConfig.getNewLimit());

        Callable<List> hotGoodsListCallable = () -> goodsService.queryByHot(0, SystemConfig.getHotLimit());

        Callable<List> brandListCallable = () -> brandService.query(0, SystemConfig.getBrandLimit());

        Callable<List> topicListCallable = () -> topicService.queryList(0, SystemConfig.getTopicLimit());

        //团购专区
        Callable<List> grouponListCallable = () -> grouponService.queryList(0, 5);

        Callable<List> floorGoodsListCallable = this::getCategoryList;

        FutureTask<List> bannerTask = new FutureTask<>(bannerListCallable);
        FutureTask<List> channelTask = new FutureTask<>(channelListCallable);
        FutureTask<List> couponListTask = new FutureTask<>(couponListCallable);
        FutureTask<List> newGoodsListTask = new FutureTask<>(newGoodsListCallable);
        FutureTask<List> hotGoodsListTask = new FutureTask<>(hotGoodsListCallable);
        FutureTask<List> brandListTask = new FutureTask<>(brandListCallable);
        FutureTask<List> topicListTask = new FutureTask<>(topicListCallable);
        FutureTask<List> grouponListTask = new FutureTask<>(grouponListCallable);
        FutureTask<List> floorGoodsListTask = new FutureTask<>(floorGoodsListCallable);

        executorService.submit(bannerTask);
        executorService.submit(channelTask);
        executorService.submit(couponListTask);
        executorService.submit(newGoodsListTask);
        executorService.submit(hotGoodsListTask);
        executorService.submit(brandListTask);
        executorService.submit(topicListTask);
        executorService.submit(grouponListTask);
        executorService.submit(floorGoodsListTask);

        Map<String, Object> entity = new HashMap<>();
        try {
            entity.put("banner", bannerTask.get());
            entity.put("channel", channelTask.get());
            entity.put("couponList", couponListTask.get());
            entity.put("newGoodsList", newGoodsListTask.get());
            entity.put("hotGoodsList", hotGoodsListTask.get());
            entity.put("brandList", brandListTask.get());
            entity.put("topicList", topicListTask.get());
            entity.put("grouponList", grouponListTask.get());
            entity.put("floorGoodsList", floorGoodsListTask.get());
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
        return entity;
    }

    private List<Map> getCategoryList() {
        List<Map> categoryList = new ArrayList<>();
        List<TinymallCategory> catL1List = categoryService.queryL1WithoutRecommend(0, SystemConfig.getCatlogListLimit());
        for (TinymallCategory catL1 : catL1List) {
            List<TinymallCategory> catL2List = categoryService.queryByPid(catL1.getId());
            List<Integer> l2List = new ArrayList<>();
            for (TinymallCategory catL2 : catL2List) {
                l2List.add(catL2.getId());
            }

            List<TinymallGoods> categoryGoods;
            if (l2List.size() == 0) {
                categoryGoods = new ArrayList<>();
            } else {
                categoryGoods = goodsService.queryByCategory(l2List, 0, SystemConfig.getCatlogMoreLimit());
            }

            Map<String, Object> catGoods = new HashMap<>();
            catGoods.put("id", catL1.getId());
            catGoods.put("name", catL1.getName());
            catGoods.put("goodsList", categoryGoods);
            categoryList.add(catGoods);
        }
        return categoryList;
    }
}

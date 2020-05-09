package com.example.tinymall.controller.wx;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.common.result.CommonResult;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.core.utils.JacksonUtil;
import com.example.tinymall.core.validator.Order;
import com.example.tinymall.core.validator.Sort;
import com.example.tinymall.entity.TinymallCollect;
import com.example.tinymall.entity.TinymallGoods;
import com.example.tinymall.model.bo.LoginUser;
import com.example.tinymall.service.TinymallCollectService;
import com.example.tinymall.service.TinymallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName WxCollectController
 * @Description
 * @Author jzf
 * @Date 2020-4-15 17:36
 */
@ResponseResult
@RestController
@RequestMapping("/wx/collect")
public class WxCollectController {

    @Autowired
    private TinymallCollectService collectService;
    @Autowired
    private TinymallGoodsService goodsService;

    /**
     * 用户收藏列表
     *
     * @param type   类型，如果是0则是商品收藏，如果是1则是专题收藏
     * @param page   分页页数
     * @param limit   分页大小
     * @return 用户收藏列表
     */
    @GetMapping("list")
    public Object list(
                       @NotNull Byte type,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        AssertUtils.notNull(loginUser,"用户未登录");
        Integer userId = loginUser.getId();

        List<TinymallCollect> collectList = collectService.queryByType(userId, type, page, limit, sort, order);

        List<Object> collects = new ArrayList<>(collectList.size());
        for (TinymallCollect collect : collectList) {
            Map<String, Object> c = new HashMap<String, Object>();
            c.put("id", collect.getId());
            c.put("type", collect.getType());
            c.put("valueId", collect.getValueId());

            TinymallGoods goods = goodsService.selectByPk(collect.getValueId());
            c.put("name", goods.getName());
            c.put("brief", goods.getBrief());
            c.put("picUrl", goods.getPicUrl());
            c.put("retailPrice", goods.getRetailPrice());

            collects.add(c);
        }

        return collectList;
    }

    /**
     * 用户收藏添加或删除
     * <p>
     * 如果商品没有收藏，则添加收藏；如果商品已经收藏，则删除收藏状态。
     *
     * @param body   请求内容，{ type: xxx, valueId: xxx }
     * @return 操作结果
     */
    @PostMapping("addordelete")
    public Object addordelete(@RequestBody String body) {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        AssertUtils.notNull(loginUser,"用户未登录");
        Integer userId = loginUser.getId();

        Byte type = JacksonUtil.parseByte(body, "type");
        Integer valueId = JacksonUtil.parseInteger(body, "valueId");
        /*if (!ObjectUtils.allNotNull(type, valueId)) {
            return ResponseUtil.badArgument();
        }*/

        TinymallCollect collect = collectService.queryByTypeAndValue(userId, type, valueId);

        if (collect != null) {
            collectService.deleteById(collect.getId());
        } else {
            collect = new TinymallCollect();
            collect.setUserId(userId);
            collect.setValueId(valueId);
            collect.setType(type);
            collectService.add(collect);
        }

        return CommonResult.success();
    }
}

package com.example.tinymall.controller.wx;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.common.result.CommonResult;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.core.utils.JacksonUtil;
import com.example.tinymall.core.validator.Order;
import com.example.tinymall.core.validator.Sort;
import com.example.tinymall.entity.TinymallCollect;
import com.example.tinymall.entity.TinymallGoods;
import com.example.tinymall.model.bo.LoginUser;
import com.example.tinymall.model.vo.FootprintVO;
import com.example.tinymall.service.TinymallCollectService;
import com.example.tinymall.service.TinymallGoodsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
     * @param pageQO   分页
     * @return 用户收藏列表
     */
    @GetMapping("list")
    public Object list(@NotNull Byte type, PageQO pageQO) {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        AssertUtils.notNull(loginUser,"用户未登录");
        Integer userId = loginUser.getId();
        TinymallCollect condition = new TinymallCollect();
        condition.setUserId(userId);
        condition.setType(type);

        pageQO.setOrderBy("c.create_time desc");
        Page<FootprintVO> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize(), pageQO.getOrderBy());
        collectService.queryByType(condition);

        return PageVO.build(page);
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
            collectService.deleteByPk(collect.getId());
        } else {
            collect = new TinymallCollect();
            collect.setUserId(userId);
            collect.setValueId(valueId);
            collect.setType(type);
            collectService.insert(collect);
        }

        return CommonResult.success();
    }
}

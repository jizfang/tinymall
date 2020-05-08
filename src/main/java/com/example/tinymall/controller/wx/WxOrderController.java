package com.example.tinymall.controller.wx;

import com.alibaba.fastjson.JSONObject;
import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.entity.TinymallOrder;
import com.example.tinymall.model.bo.LoginUser;
import com.example.tinymall.model.bo.OrderInfo;
import com.example.tinymall.model.bo.UserCartInfo;
import com.example.tinymall.model.dto.UserOrderParam;
import com.example.tinymall.service.WxOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName WxOrderController
 * @Description
 * @Author jzf
 * @Date 2020-4-23 11:02
 */
@RestController
@RequestMapping("/wx/order")
@ResponseResult
public class WxOrderController {

    @Autowired
    private WxOrderService wxOrderService;

    /**
     * 提交订单
     *
     * @param userCartInfo   订单信息，{ cartId：xxx, addressId: xxx, couponId: xxx, message: xxx, grouponRulesId: xxx,  grouponLinkId: xxx}
     * @return 提交订单操作结果
     */
    @PostMapping("submit")
    public Object submit(@RequestBody UserCartInfo userCartInfo){
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        Integer userId = Integer.valueOf(loginUser.getId());
        return wxOrderService.submit(userId, userCartInfo);
    }

    /**
     * 付款订单的预支付会话标识
     *
     * @param orderInfo   订单信息，{ orderId：xxx }
     * @return 支付订单ID
     */
    @PostMapping("prepay")
    public Object prepay(@RequestBody OrderInfo orderInfo, HttpServletRequest request) {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        Integer userId = Integer.valueOf(loginUser.getId());
        return wxOrderService.prepay(userId, orderInfo, request);
    }

    @GetMapping("list")
    public PageVO<TinymallOrder> list(PageQO pageQO,UserOrderParam userOrderParam){
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        Integer userId = Integer.valueOf(loginUser.getId());
        if(userOrderParam == null){
            userOrderParam = new UserOrderParam();
        }
        userOrderParam.setUserId(userId);
        pageQO.setCondition(userOrderParam);
        return wxOrderService.list(pageQO);
    }

    /**
     * 订单详情
     *
     * @param orderId 订单ID
     * @return 订单详情
     */
    @GetMapping("detail")
    public Object detail(Integer orderId) {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        Integer userId = Integer.valueOf(loginUser.getId());
        return wxOrderService.detail(userId, orderId);
    }
}

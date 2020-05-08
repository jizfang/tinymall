package com.example.tinymall.controller.wx;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.model.bo.LoginUser;
import com.example.tinymall.service.TinymallOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WxUserController
 * @Description
 * @Author jzf
 * @Date 2020-4-11 17:41
 */
@ResponseResult
@RestController
@RequestMapping("/wx/user")
public class WxUserController {
    @Autowired
    private TinymallOrderService orderService;

    /**
     * 用户个人页面数据
     * <p>
     * 目前是用户订单统计信息
     *
     * @return 用户个人页面数据
     */
    @GetMapping("index")
    public Object list() {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        AssertUtils.notNull(loginUser,"用户未登录");
        Integer userId = loginUser.getId();

        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("order", orderService.orderInfo(userId));
        return data;
    }
}

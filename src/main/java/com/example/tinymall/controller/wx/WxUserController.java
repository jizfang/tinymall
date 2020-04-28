package com.example.tinymall.controller.wx;

import com.example.tinymall.core.annotation.LoginUser;
import com.example.tinymall.core.util.ResponseUtil;
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
     * @param userId 用户ID
     * @return 用户个人页面数据
     */
    @GetMapping("index")
    public Object list(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("order", orderService.orderInfo(userId));
        return ResponseUtil.ok(data);
    }
}

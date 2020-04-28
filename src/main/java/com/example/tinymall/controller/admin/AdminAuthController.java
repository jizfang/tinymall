package com.example.tinymall.controller.admin;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.core.constants.ResponseCode;
import com.example.tinymall.core.util.IpUtil;
import com.example.tinymall.core.util.ResponseMsg;
import com.example.tinymall.core.util.bcrypt.BCryptPasswordEncoder;
import com.example.tinymall.domain.TinymallAdmin;
import com.example.tinymall.domain.dto.UserInfo;
import com.example.tinymall.domain.vo.UserLoginInfo;
import com.example.tinymall.manager.UserTokenManager;
import com.example.tinymall.service.TinymallAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminAuthController
 * @Description
 * @Author jzf
 * @Date 2020-4-28 15:03
 */
@ResponseResult
@RestController
@RequestMapping("/admin/user")
public class AdminAuthController {

    @Autowired
    private TinymallAdminService userService;

    @PostMapping("/login")
    @ResponseStatus
    public Object login(@RequestBody UserLoginInfo userLoginInfo, HttpServletRequest request) {
        String username = userLoginInfo.getUsername();
        String password = userLoginInfo.getPassword();
        if (username == null || password == null) {
            return ResponseMsg.badArgument();
        }

        List<TinymallAdmin> userList = userService.queryByUsername(username);
        TinymallAdmin user = null;
        if (userList.size() > 1) {
            return ResponseMsg.serious();
        } else if (userList.size() == 0) {
            return ResponseMsg.createByErrorCodeMessage(ResponseCode.USER_NOT_FOUND.getMsgCode(), "账号不存在");
        } else {
            user = userList.get(0);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, user.getPassword())) {
            return ResponseMsg.createByErrorCodeMessage(ResponseCode.PASSWORD_ERROR.getMsgCode(), "账号不存在");
        }

        // 更新登录情况
        user.setLastLoginTime(LocalDateTime.now());
        user.setLastLoginIp(IpUtil.getIpAddr(request));
        if (userService.updateById(user) == 0) {
            return ResponseMsg.createByErrorCodeMessage(ResponseCode.SYSTEM_ERROR.getMsgCode(), "账号不存在");
        }

        // userInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(username);
        userInfo.setAvatarUrl(user.getAvatar());

        // token
        String token = UserTokenManager.generateToken(user.getId());

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", token);
        result.put("userInfo", userInfo);
        //return ResponseMsg.createBySuccess(result);
        return result;
    }
}

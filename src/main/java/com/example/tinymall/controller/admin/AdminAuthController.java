package com.example.tinymall.controller.admin;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.core.annotation.LoginUser;
import com.example.tinymall.core.constants.ResponseCode;
import com.example.tinymall.core.util.*;
import com.example.tinymall.domain.TinymallAdmin;
import com.example.tinymall.domain.dto.UserInfo;
import com.example.tinymall.domain.vo.UserLoginInfo;
import com.example.tinymall.service.TinymallAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@ResponseStatus(HttpStatus.OK)
public class AdminAuthController {

    @Autowired
    private TinymallAdminService userService;

    @PostMapping("/login")
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

        boolean result = MD5Util.validDigest(password,user.getPassword());
        if (!result) {
            return ResponseMsg.createByErrorCodeMessage(ResponseCode.PASSWORD_ERROR.getMsgCode(), ResponseCode.PASSWORD_ERROR.getMessage());
        }

        // 更新登录情况
        user.setLastLoginTime(LocalDateTime.now());
        user.setLastLoginIp(IpUtil.getIpAddr(request));
        if (userService.updateById(user) == 0) {
            return ResponseMsg.createByErrorCodeMessage(ResponseCode.SYSTEM_ERROR.getMsgCode(), "账号不存在");
        }

        // userInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setName(username);
        userInfo.setAvatarUrl(user.getAvatar());

        // token
        String token = LoginTokenHelper.generateToken(user.getId());

        Map<Object, Object> resultMap = new HashMap<Object, Object>();
        resultMap.put("token", token);
        resultMap.put("userInfo", userInfo);
        return resultMap;
    }

    @PostMapping("logout")
    public Object logout(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        return ResponseUtil.ok();
    }

    @GetMapping("info")
    public Object logout(String token) {
        if (StringUtil.isEmpty(token)) {
            return ResponseUtil.unlogin();
        }
        int userId = LoginTokenHelper.getUserId(token);
        TinymallAdmin tinymallAdmin = userService.getByUserId(userId);
        UserInfo userInfo = new UserInfo();
        userInfo.setAvatarUrl(tinymallAdmin.getAvatar());
        userInfo.setName(tinymallAdmin.getUsername());
        userInfo.setRoles(tinymallAdmin.getRoleIds());
        return userInfo;
    }
}

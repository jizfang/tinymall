package com.example.tinymall.manager;

import com.example.tinymall.core.util.JwtHelper;

/**
 * @ClassName UserTokenManager
 * @Description
 * @Author jzf
 * @Date 2020-4-14 20:24
 */
public class UserTokenManager {
    public static String generateToken(Integer id) {
        JwtHelper jwtHelper = new JwtHelper();
        return jwtHelper.createToken(id);
    }
    public static Integer getUserId(String token) {
        JwtHelper jwtHelper = new JwtHelper();
        Integer userId = jwtHelper.verifyTokenAndGetUserId(token);
        if(userId == null || userId == 0){
            return null;
        }
        return userId;
    }
}

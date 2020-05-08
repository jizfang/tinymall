package com.example.tinymall.service;

import com.example.tinymall.model.vo.LoginVO;
import com.example.tinymall.model.vo.UserLoginInfo;

/**
 * @ClassName LoginService
 * @Description 登录服务
 * @Author jzf
 * @Date 2020-4-29 11:29
 */
public interface LoginService {
    LoginVO login(UserLoginInfo loginInfo);

    void logout();
}

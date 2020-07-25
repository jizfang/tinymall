package com.example.tinymall.service.impl;

import com.example.tinymall.model.vo.LoginVO;
import com.example.tinymall.model.vo.UserLoginInfo;
import com.example.tinymall.service.LoginService;
import com.example.tinymall.service.LoginTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @ClassName LoginServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-29 11:33
 */
@Service
@Primary
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginTokenService loginTokenService;

    @Override
    public LoginVO login(UserLoginInfo loginInfo) {
        return null;
    }

    @Override
    public void logout() {

    }
}

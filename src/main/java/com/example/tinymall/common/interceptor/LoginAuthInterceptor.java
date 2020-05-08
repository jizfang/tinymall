package com.example.tinymall.common.interceptor;

import com.example.tinymall.common.Exceptions.BusinessException;
import com.example.tinymall.common.annotation.LoginAuth;
import com.example.tinymall.common.enums.CacheKeyEnum;
import com.example.tinymall.common.enums.ResultCode;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.core.utils.StringUtil;
import com.example.tinymall.model.bo.LoginToken;
import com.example.tinymall.model.bo.LoginUser;
import com.example.tinymall.service.LoginTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @ClassName LoginedAuthInterceptoe
 * @Description 登录拦截器
 * @Author jzf
 * @Date 2020-4-29 9:02
 */
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {
    @Autowired
    private LoginTokenService loginTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            if (clazz.isAnnotationPresent(LoginAuth.class) || method.isAnnotationPresent(LoginAuth.class)) {

                //直接获取登录用户（防止请求转发时，第二次查询）
                LoginUser loginedUser = LoginTokenHelper.getLoginUserFromRequest();
                if (loginedUser != null) {
                    return true;
                }

                //获取登录TOKEN ID
                String loginTokenId = LoginTokenHelper.getLoginTokenId();
                if (StringUtil.isEmpty(loginTokenId)) {
                    throw new BusinessException(ResultCode.USER_NOT_LOGGED_IN);
                }

                //获取登录TOKEN信息
                LoginToken loginToken = loginTokenService.getById(loginTokenId,CacheKeyEnum.VALUE_LOGIN_TOKENS.code());
                if (loginToken == null) {
                    throw new BusinessException(ResultCode.USER_NOT_LOGGED_IN);
                }

                //登录用户信息放入请求对象，方便后续controller中获取
                LoginTokenHelper.addLoginTokenToRequest(loginToken);
                return true;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

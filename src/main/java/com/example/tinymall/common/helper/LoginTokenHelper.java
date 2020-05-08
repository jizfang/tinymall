package com.example.tinymall.common.helper;

import com.example.tinymall.common.annotation.LoginAuth;
import com.example.tinymall.core.utils.CookieUtil;
import com.example.tinymall.core.utils.JwtHelper;
import com.example.tinymall.core.utils.RequestContextUtil;
import com.example.tinymall.core.utils.StringUtil;
import com.example.tinymall.model.bo.LoginToken;
import com.example.tinymall.model.bo.LoginUser;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @ClassName LoginTokenHelper
 * @Description 登录TOKEN辅助类
 * @Author jzf
 * @Date 2020-4-29 9:25
 */
public class LoginTokenHelper {
    private final static String SECRET_KEY = "Ld4Dl5f9OoYTezPK";

    private final static String LOGIN_TOKEN_COOKIE_NAME = "X-Tinymall-Token";

    private final static String LOGIN_TOKEN_KEY = "X-Tinymall-Token";

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

    /**
     * 根据登录的相关信息生成TOKEN ID
     */
    public static String generateId(String ip, String platform, Date loginTime, long ttl) {
        StringBuilder noEncodeLoginTokenId = new StringBuilder(ip)
                .append(platform)
                .append(loginTime)
                .append(ttl);

        return DigestUtils.sha256Hex(SECRET_KEY + DigestUtils.md5Hex(noEncodeLoginTokenId.toString()) + DigestUtils.md5Hex(SECRET_KEY));
    }

    /**
     * 添加登录TOKEN的ID信息到COOKIE中
     */
    public static void addLoginTokenIdToCookie(String loginTokenId, Integer expiredTimeSec) {
        HttpServletResponse response = RequestContextUtil.getResponse();
        CookieUtil.addCookie(response, LOGIN_TOKEN_COOKIE_NAME, loginTokenId, expiredTimeSec == null ? -1 : expiredTimeSec, true);
    }

    /**
     * 清理登录账号信息从COOKIE中
     */
    public static void delLoginTokenIdFromCookie() {
        HttpServletRequest request = RequestContextUtil.getRequest();
        HttpServletResponse response = RequestContextUtil.getResponse();

        CookieUtil.delCookie(request, response, LOGIN_TOKEN_COOKIE_NAME);
    }

    /**
     * 获取登录的TOKEN的ID（取头信息或Cookie中）
     */
    public static String getLoginTokenId() {
        HttpServletRequest request = RequestContextUtil.getRequest();
        String token = request.getHeader(LOGIN_TOKEN_COOKIE_NAME);
        if (StringUtil.isEmpty(token)) {
            token = CookieUtil.getCookieValue(request, LOGIN_TOKEN_COOKIE_NAME, true);
        }
        return token;
    }

    /**
     * 将登录TOKEN信息放入请求对象
     */
    public static void addLoginTokenToRequest(LoginToken loginToken) {
        RequestContextUtil.getRequest().setAttribute(LOGIN_TOKEN_KEY, loginToken);
    }

    /**
     * 获取登录用户信息从请求对象 备注：使用该方法时需要在对应controller类或方法上加{@link LoginAuth}}注解
     */
    public static LoginUser getLoginUserFromRequest() {
        String loginToken = getLoginTokenFromRequest();
        if (StringUtil.isEmpty(loginToken)) {
            return null;
        }
        Integer userId = getUserId(loginToken);
        LoginUser loginUser = new LoginUser();
        if(userId == null){
            loginUser.setId(1);
        }else {
            loginUser.setId(userId);
        }
        return loginUser;
    }

    /**
     * 获取登录TOKEN信息从请求对象 备注：使用该方法时需要在对应controller类或方法上加{@link LoginAuth}}注解
     */
    public static String getLoginTokenFromRequest() {
        String loginToken = RequestContextUtil.getRequest().getHeader(LOGIN_TOKEN_KEY);
        if (StringUtil.isEmpty(loginToken)) {
            return null;
        }

        return loginToken;
    }
}

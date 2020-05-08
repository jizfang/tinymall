package com.example.tinymall.controller.wx;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.example.tinymall.common.Exceptions.BusinessException;
import com.example.tinymall.common.annotation.LoginAuth;
import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.enums.BusinessExceptionEnum;
import com.example.tinymall.common.enums.ResultCode;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.common.result.CommonResult;
import com.example.tinymall.core.utils.*;
import com.example.tinymall.entity.TinymallUser;
import com.example.tinymall.model.bo.LoginUser;
import com.example.tinymall.model.bo.RegisterInfo;
import com.example.tinymall.model.dto.UserInfo;
import com.example.tinymall.model.dto.WxLoginInfo;
import com.example.tinymall.model.vo.UserLoginInfo;
import com.example.tinymall.service.CouponAssignService;
import com.example.tinymall.service.TinymallUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.tinymall.core.utils.WxResponseCode.*;

/**
 * @ClassName WxAuthController
 * @Description
 * @Author jzf
 * @Date 2020-4-14 19:55
 */
@Api(description = "用户权限操作接口")
@RestController
@RequestMapping("/wx/auth")
@ResponseResult
public class WxAuthController {
    @Autowired
    private TinymallUserService userService;

    @Autowired
    private WxMaService wxService;

    @Autowired
    private CouponAssignService couponAssignService;
    /**
     * 微信登录
     *
     * @param wxLoginInfo 请求内容，{ code: xxx, userInfo: xxx }
     * @param request     请求对象
     * @return 登录结果
     */
    @PostMapping("login_by_weixin")
    @ResponseStatus(HttpStatus.OK)
    public Object loginByWeixin(@RequestBody WxLoginInfo wxLoginInfo, HttpServletRequest request) {
        String code = wxLoginInfo.getCode();
        UserInfo userInfo = wxLoginInfo.getUserInfo();
        AssertUtils.notBlank(code,ResultCode.PARAM_IS_INVALID);
        AssertUtils.notNull(userInfo,"用户不存在");

        String sessionKey = null;
        String openId = null;
        try {
            WxMaJscode2SessionResult result = this.wxService.getUserService().getSessionInfo(code);
            sessionKey = result.getSessionKey();
            openId = result.getOpenid();
        } catch (Exception e) {
            e.printStackTrace();
        }

        AssertUtils.notBlank(sessionKey,ResultCode.USER_NOT_LOGGED_IN);
        AssertUtils.notBlank(openId,ResultCode.USER_NOT_LOGGED_IN);

        TinymallUser user = userService.queryByOid(openId);
        if (user == null) {
            user = new TinymallUser();
            user.setUsername(openId);
            user.setPassword(openId);
            user.setWeixinOpenid(openId);
            user.setAvatar(userInfo.getAvatarUrl());
            user.setNickname(userInfo.getName());
            user.setGender(userInfo.getGender());
            user.setUserLevel((byte) 0);
            user.setStatus((byte) 0);
            user.setLastLoginTime(LocalDateTime.now());
            user.setLastLoginIp(IpUtil.getIpAddr(request));
            user.setSessionKey(sessionKey);

            userService.add(user);

            // 新用户发送注册优惠券
            couponAssignService.assignForRegister(user.getId());
        } else {
            user.setLastLoginTime(LocalDateTime.now());
            user.setLastLoginIp(IpUtil.getIpAddr(request));
            user.setSessionKey(sessionKey);
            AssertUtils.isFalse(userService.updateById(user) == 0,"更新失败");
        }

        // token
        String token = LoginTokenHelper.generateToken(user.getId());

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", token);
        result.put("userInfo", userInfo);
        return result;
    }

    /**
     * 账号登录
     *
     * @param userLoginInfo    请求内容，{ username: xxx, password: xxx }
     * @param request 请求对象
     * @return 登录结果
     */
    @ApiOperation(value = "用户名密码登录", notes="用户名密码登录")
    @ApiImplicitParam(name = "userLoginInfo", value = "用户登录信息", paramType = "UserLoginInfo", required = true, dataType = "UserLoginInfo")
    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> login(@RequestBody UserLoginInfo userLoginInfo, HttpServletRequest request) {
        String username = userLoginInfo.getUsername();
        String password = userLoginInfo.getPassword();
        if (username == null || password == null) {
            throw new BusinessException(BusinessExceptionEnum.PARAMETER_INVALID.getResultCode());
        }

        List<TinymallUser> userList = userService.queryByUsername(username);
        TinymallUser user = null;
        if (userList.size() > 1) {
            throw new BusinessException(ResultCode.USER_NOT_EXIST);
        } else if (userList.size() == 0) {
            throw new BusinessException(ResultCode.USER_NOT_EXIST);
        } else {
            user = userList.get(0);
        }

        boolean result = MD5Util.validDigest(password,user.getPassword());
        if (!result) {
            throw new BusinessException(ResultCode.USER_LOGIN_ERROR);
        }

        // 更新登录情况
        user.setLastLoginTime(LocalDateTime.now());
        user.setLastLoginIp(IpUtil.getIpAddr(request));
        if (userService.updateById(user) == 0) {
            throw new BusinessException(ResultCode.USER_NOT_EXIST);
        }

        // userInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setName(username);
        userInfo.setAvatarUrl(user.getAvatar());

        // token
        String token = LoginTokenHelper.generateToken(user.getId());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", token);
        resultMap.put("userInfo", userInfo);
        return resultMap;
    }

    /**
     * 请求注册验证码
     *
     * TODO
     * 这里需要一定机制防止短信验证码被滥用
     *
     * @param body 手机号码 { mobile }
     * @return
     */
    @PostMapping("regCaptcha")
    @ResponseStatus(HttpStatus.OK)
    public Object registerCaptcha(@RequestBody String body) {
        /*String phoneNumber = JacksonUtil.parseString(body, "mobile");
        if (StringUtils.isEmpty(phoneNumber)) {
            return ResponseUtil.badArgument();
        }
        if (!RegexUtil.isMobileExact(phoneNumber)) {
            return ResponseUtil.badArgumentValue();
        }

        if (!notifyService.isSmsEnable()) {
            return ResponseUtil.fail(AUTH_CAPTCHA_UNSUPPORT, "小程序后台验证码服务不支持");
        }
        String code = CharUtil.getRandomNum(6);
        boolean successful = CaptchaCodeManager.addToCache(phoneNumber, code);
        if (!successful) {
            return ResponseUtil.fail(AUTH_CAPTCHA_FREQUENCY, "验证码未超时1分钟，不能发送");
        }
        notifyService.notifySmsTemplate(phoneNumber, NotifyType.CAPTCHA, new String[]{code});*/

        return CommonResult.success();
    }

    /**
     * 账号注册
     *
     * @param registerInfo    请求内容
     *                {
     *                username: xxx,
     *                password: xxx,
     *                mobile: xxx
     *                code: xxx
     *                }
     *                其中code是手机验证码，目前还不支持手机短信验证码
     * @param request 请求对象
     * @return 登录结果
     * 成功则
     * {
     * errno: 0,
     * errmsg: '成功',
     * data:
     * {
     * token: xxx,
     * tokenExpire: xxx,
     * userInfo: xxx
     * }
     * }
     * 失败则 { errno: XXX, errmsg: XXX }
     */
    @PostMapping("register")
    @ResponseStatus(HttpStatus.OK)
    public Object register(@RequestBody RegisterInfo registerInfo, HttpServletRequest request) {
        String username = registerInfo.getUsername();
        String password = registerInfo.getPassword();
        String mobile = registerInfo.getMobile();
        // 如果是小程序注册，则必须非空
        // 其他情况，可以为空
        String wxCode = registerInfo.getWxCode();

        AssertUtils.isFalse(StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(mobile),"参数无效");

        List<TinymallUser> userList = userService.queryByUsername(username);
        AssertUtils.isFalse(userList.size() > 0,ResultCode.USER_HAS_EXISTED);

        userList = userService.queryByMobile(mobile);
        AssertUtils.isFalse(userList.size() > 0,ResultCode.USER_HAS_EXISTED);
        AssertUtils.isTrue(RegexUtil.isMobileExact(mobile), "手机号格式不正确");

        //判断验证码是否正确
        /*String cacheCode = CaptchaCodeManager.getCachedCaptcha(mobile);
        if (cacheCode == null || cacheCode.isEmpty() || !cacheCode.equals(code)) {
            return ResponseUtil.fail(AUTH_CAPTCHA_UNMATCH, "验证码错误");
        }*/

        String openId = "";
        // 非空，则是小程序注册
        // 继续验证openid
        if(!StringUtils.isEmpty(wxCode)) {
            try {
                WxMaJscode2SessionResult result = this.wxService.getUserService().getSessionInfo(wxCode);
                openId = result.getOpenid();
            } catch (Exception e) {
                e.printStackTrace();
                return new BusinessException("openid 获取失败");
            }
            userList = userService.queryByOpenid(openId);
            AssertUtils.isFalse(userList.size() > 1,ResultCode.DATA_ALREADY_EXISTED);

            if (userList.size() == 1) {
                TinymallUser checkUser = userList.get(0);
                String checkUsername = checkUser.getUsername();
                String checkPassword = checkUser.getPassword();
                AssertUtils.isTrue(!checkUsername.equals(openId) || !checkPassword.equals(openId), "openid已绑定账号");
            }
        }

        TinymallUser user = null;
        user = new TinymallUser();
        user.setUsername(username);
        user.setPassword(MD5Util.encode(password));
        user.setMobile(mobile);
        user.setWeixinOpenid(openId);
        user.setAvatar("https://yanxuan.nosdn.127.net/80841d741d7fa3073e0ae27bf487339f.jpg?imageView&quality=90&thumbnail=64x64");
        user.setNickname(username);
        user.setGender((byte) 0);
        user.setUserLevel((byte) 0);
        user.setStatus((byte) 0);
        user.setLastLoginTime(LocalDateTime.now());
        user.setLastLoginIp(IpUtil.getIpAddr(request));
        userService.add(user);

        // 给新用户发送注册优惠券
        couponAssignService.assignForRegister(user.getId());

        // userInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setName(username);
        userInfo.setAvatarUrl(user.getAvatar());

        // token
        String token = LoginTokenHelper.generateToken(user.getId());

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", token);
        result.put("userInfo", userInfo);
        return result;
    }

    @PostMapping("logout")
    @ResponseStatus(HttpStatus.OK)
    @LoginAuth
    public Object logout() {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();

        return CommonResult.success();
    }
}

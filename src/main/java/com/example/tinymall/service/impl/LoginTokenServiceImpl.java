package com.example.tinymall.service.impl;

import com.example.tinymall.common.enums.CacheKeyEnum;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.core.util.RedisUtil;
import com.example.tinymall.domain.bo.LoginToken;
import com.example.tinymall.service.LoginTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName LoginTokenServiceImpl
 * @Description token service
 * @Author jzf
 * @Date 2020-4-29 9:18
 */
@Slf4j
@Service
public class LoginTokenServiceImpl implements LoginTokenService {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public LoginToken add(LoginToken loginToken,String loginTokenCacheKeyPrefix) {
        Assert.notNull(loginToken, "loginToken is not null");
        Assert.notNull(loginToken.getLoginUser(), "loginToken.getLoginUser() is not null");
        String token = LoginTokenHelper.generateId(loginToken.getIp(), loginToken.getPlatform(), loginToken.getCreateTime(), loginToken.getTtl());
        loginToken.setId(token);
        redisUtil.set(getLoginTokenCacheKey(loginTokenCacheKeyPrefix,loginToken.getId()),loginToken, CacheKeyEnum.VALUE_LOGIN_TOKENS.sec());
        return loginToken;
    }

    @Override
    public void deleteById(String id,String loginTokenCacheKeyPrefix) {
        Assert.notNull(id, "id is not null");

        redisUtil.del(this.getLoginTokenCacheKey(loginTokenCacheKeyPrefix,id));
    }

    @Override
    public LoginToken getById(String id,String loginTokenCacheKeyPrefix) {
        Assert.notNull(id, "id is not null");

        return (LoginToken) redisUtil.get(this.getLoginTokenCacheKey(loginTokenCacheKeyPrefix,id));
    }

    @Override
    public long ttl(String id,String loginTokenCacheKeyPrefix) {
        Assert.notNull(id, "id is not null");

        return redisUtil.getExpire(this.getLoginTokenCacheKey(loginTokenCacheKeyPrefix,id));
    }

    private String getLoginTokenCacheKey(String loginTokenCacheKeyPrefix,String token) {
        return loginTokenCacheKeyPrefix + token;
    }
}

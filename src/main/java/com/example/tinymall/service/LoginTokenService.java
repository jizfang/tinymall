package com.example.tinymall.service;

import com.example.tinymall.model.bo.LoginToken;

/**
 * @ClassName LoginTokenService
 * @Description 用户登录token服务
 * @Author jzf
 * @Date 2020-4-29 9:12
 */
public interface LoginTokenService {
    /**
     *
     * Add login token.
     *
     * @param loginToken the login token
     * @return the login token
     */
    LoginToken add(LoginToken loginToken,String loginTokenCacheKeyPrefix);

    /**
     *
     * Delete by id.
     *
     * @param id the id
     */
    void deleteById(String id,String loginTokenCacheKeyPrefix);

    /**
     *
     * Get by id login token.
     *
     * @param id the id
     * @return the login token
     */

    LoginToken getById(String id,String loginTokenCacheKeyPrefix);

    /**
     *
     * Ttl long.
     *
     * @param id the id
     * @return the long
     */
    long ttl(String id,String loginTokenCacheKeyPrefix);

}

package com.example.tinymall.common.enums;

import com.example.tinymall.core.util.StringUtil;

/**
 * @ClassName CacheKeyEnum
 * @Description 统一定义缓存KEY
 * @Author jzf
 * @Date 2020-4-29 9:21
 */
public enum CacheKeyEnum {
    /* ---------------用户相关缓存------------------ */
    /**
     * 登录TOKEN缓存key
     */
    VALUE_LOGIN_TOKENS("tinymall:login_tokens:", TimeEnum.ONE_WEEK.sec()),

    /**
     * 用户缓存
     */
    VALUE_USERS("tinymall:user:profile:%s", TimeEnum.ONE_WEEK.sec());

    /**
     * 缓存key
     */
    private String code;

    /**
     * 过期时间（单位：秒）
     */
    private Integer sec;

    CacheKeyEnum(String code, Integer sec) {
        this.code = code;
        this.sec = sec;
    }

    public String code() {
        return this.code;
    }

    public Integer sec() {
        return this.sec;
    }

    @Override
    public String toString() {
        return this.name();
    }

    public String formatKey(Object... args) {
        int requiredNum = StringUtil.getSubStrCount(this.code, "%s");
        boolean isCorrectArgsNum = requiredNum != 0 && (args == null || args.length != requiredNum);
        if (isCorrectArgsNum) {
            throw new IllegalArgumentException("The number of parameters is not equal to the required number.");
        }
        return String.format(this.code, args);
    }
}

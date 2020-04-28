package com.example.tinymall.common.constants;

import com.example.tinymall.common.enums.ApiStyleEnum;
import com.example.tinymall.common.enums.CallSourceEnum;

/**
 * @ClassName HeaderConstants
 * @Description 请求头的key
 * @Author jzf
 * @Date 2020-4-28 17:03
 */
public class HeaderConstants {
    /**
     * 用户的登录token
     */
    public static final String X_TINYMALL_TOKEN = "X-Tinymall-Token";

    /**
     * api的版本号
     */
    public static final String API_VERSION = "Api-Version";

    /**
     * app版本号
     */
    public static final String APP_VERSION = "App-Version";

    /**
     * 调用来源 {@link CallSourceEnum}
     */
    public static final String CALL_SOURCE = "Call-Source";

    /**
     * API的返回格式 {@link ApiStyleEnum}
     */
    public static final String API_STYLE = "Api-Style";
}

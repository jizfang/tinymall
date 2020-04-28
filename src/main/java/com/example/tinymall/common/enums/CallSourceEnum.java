package com.example.tinymall.common.enums;

/**
 * @ClassName CallSourceEnum
 * @Description 调用来源枚举
 * @Author jzf
 * @Date 2020-4-28 17:05
 */
public enum CallSourceEnum {
    /**
     * WEB网站
     */
    WEB,
    /**
     * PC客户端
     */
    PC,
    /**
     * 微信公众号
     */
    WECHAT,
    /**
     * IOS平台
     **/
    IOS,
    /**
     * 安卓平台
     */
    ANDROID;

    public static boolean isValid(String name) {
        for (CallSourceEnum callSource : CallSourceEnum.values()) {
            if (callSource.name().equals(name)) {
                return true;
            }
        }
        return false;
    }
}

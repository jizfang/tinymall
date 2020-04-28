package com.example.tinymall.common.enums;

/**
 * @ClassName ApiStyleEnum
 * @Description 接口返回值风格样式枚举类
 * @Author jzf
 * @Date 2020-4-28 16:27
 */
public enum ApiStyleEnum {
    NONE;

    public static boolean isValid(String name) {
        for (ApiStyleEnum callSource : ApiStyleEnum.values()) {
            if (callSource.name().equals(name)) {
                return true;
            }
        }
        return false;
    }
}

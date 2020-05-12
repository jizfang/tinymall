package com.example.tinymall.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName ValidatorUtils
 * @Description 校验类
 * @Author jzf
 * @Date 2020-5-12 8:58
 */
public class ValidatorUtils {
    /**
     * 校验手机号码格式是否正确
     * @param mobile  需要校验的手机号码
     * @return  boolean
     */
    public static boolean isMobile(String mobile) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if (mobile.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(mobile);
            boolean isMatch = m.matches();
            if (isMatch) {
                return true;
            } else {
                return false;
            }
        }
    }
}

package com.example.tinymall.common.annotation;

import java.lang.annotation.*;

/**
 * @ClassName LoginedAuth
 * @Description 登录权限验证
 * @Author jzf
 * @Date 2020-4-28 17:55
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginAuth {
}

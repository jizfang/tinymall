package com.example.tinymall.common.annotation;

import com.example.tinymall.common.result.CommonResult;
import com.example.tinymall.common.result.Result;

import java.lang.annotation.*;

/**
 * @ClassName ResponseResult
 * @Description 接口返回结果
 * @Author jzf
 * @Date 2020-4-28 15:56
 */
@Target({ ElementType.TYPE,ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {
    Class<? extends Result> value() default CommonResult.class;
}

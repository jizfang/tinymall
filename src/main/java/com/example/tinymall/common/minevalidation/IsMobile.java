package com.example.tinymall.common.minevalidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @ClassName IsMobile
 * @Description 电话号码格式校验
 * @Author jzf
 * @Date 2020-5-12 8:43
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Constraint(validatedBy = {IsMobileValidator.class})
public @interface IsMobile {

    /**
     * 是否需要校验
     * @return
     */
    boolean required() default true;

    String message() default "手机号码格式错误";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

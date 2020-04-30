package com.example.tinymall.core.annotation;

import java.lang.annotation.*;

/**
 * @ClassName FieldAlias
 * @Description 别名注解
 * @Author jzf
 * @Date 2020-4-30 14:59
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(FieldAlias.FieldAliases.class)
public @interface FieldAlias {

    String value();

    Class<?>[] sourceClass() default { };

    /**
     * @desc 别名注解复数
     *
     * @author zhuamer
     * @since 7/6/2017 3:13 PM
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface FieldAliases {

        FieldAlias[] value();

    }
}

package com.example.tinymall.common.minevalidation;

import com.example.tinymall.core.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName IsMobileValidator
 * @Description 自定义手机号注解解析器
 * @Author jzf
 * @Date 2020-5-12 8:43
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    public boolean required = true;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        // value为空时进行校验
        if (StringUtils.isBlank(value)) {
            if (required) {
                return false;
            }
            return true;
        } else {
            // 不为空时进行校验
            return ValidatorUtils.isMobile(value);
        }
    }
}

package com.example.tinymall.common.handler;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.constants.HeaderConstants;
import com.example.tinymall.common.enums.ApiStyleEnum;
import com.example.tinymall.common.interceptor.ResponseResultInterceptor;
import com.example.tinymall.common.result.CommonResult;
import com.example.tinymall.common.result.DefaultErrorResult;
import com.example.tinymall.common.result.Result;
import com.example.tinymall.core.utils.JsonUtil;
import com.example.tinymall.core.utils.RequestContextUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ResponseResultHandler
 * @Description 接口响应处理器
 * @Author jzf
 * @Date 2020-4-28 16:17
 */
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        HttpServletRequest request = RequestContextUtil.getRequest();
        ResponseResult responseResultAnn = (ResponseResult) request.getAttribute(ResponseResultInterceptor.RESPONSE_RESULT);
        return responseResultAnn != null && !ApiStyleEnum.NONE.name().equalsIgnoreCase(request.getHeader(HeaderConstants.API_STYLE));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ResponseResult responseResultAnn = (ResponseResult) RequestContextUtil.getRequest().getAttribute(ResponseResultInterceptor.RESPONSE_RESULT);

        Class<? extends Result> resultClazz = responseResultAnn.value();

        if (resultClazz.isAssignableFrom(CommonResult.class)) {
            if (body instanceof DefaultErrorResult) {
                DefaultErrorResult defaultErrorResult = (DefaultErrorResult) body;
                return CommonResult.builder()
                        .code(defaultErrorResult.getCode())
                        .msg(defaultErrorResult.getMessage())
                        .data(defaultErrorResult.getErrors())
                        .build();
            } else if (body instanceof String) {
                return JsonUtil.object2Json(CommonResult.success(body));
            }

            return CommonResult.success(body);
        }

        return body;
    }
}

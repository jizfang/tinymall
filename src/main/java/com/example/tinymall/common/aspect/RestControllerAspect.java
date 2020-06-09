package com.example.tinymall.common.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.tinymall.common.constants.HeaderConstants;
import com.example.tinymall.common.handler.GlobalExceptionHandler;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.core.utils.IpUtil;
import com.example.tinymall.core.utils.RequestContextUtil;
import com.example.tinymall.model.bo.LoginUser;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @ClassName RestControllerAspect
 * @Description 日志切面，统一打印日志
 * @Author jzf
 * @Date 2020-4-30 14:08
 */
@Aspect
@Component
@Slf4j
public class RestControllerAspect {

    /**
     * 环绕通知
     * @param joinPoint
     * @return
     */
    @Around("@within(org.springframework.web.bind.annotation.RestController) || @annotation(org.springframework.web.bind.annotation.RestController)")
    public Object apiLog(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        boolean logFlag = this.needToLog(method);
        if(!logFlag){
            return joinPoint.proceed();
        }

        HttpServletRequest request = RequestContextUtil.getRequestAttributes().getRequest();
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();

        String ip = IpUtil.getRealIp(request);
        String methodName = this.getMethodName(joinPoint);
        String params = this.getParamsJson(joinPoint);
        String requester = loginUser == null ? "unknown" : String.valueOf(loginUser.getId());

        String callSource = request.getHeader(HeaderConstants.CALL_SOURCE);
        String appVersion = request.getHeader(HeaderConstants.APP_VERSION);
        String apiVersion = request.getHeader(HeaderConstants.API_VERSION);
        String userAgent = request.getHeader("user-agent");

        log.info("Started request requester [{}] method [{}] params [{}] IP [{}] callSource [{}] appVersion [{}] apiVersion [{}] userAgent [{}]", requester, methodName, params, ip, callSource, appVersion, apiVersion, userAgent);
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
         log.info("Ended request requester [{}] method [{}] params[{}] response is [{}] cost [{}] millis ",
                requester, methodName, params, this.deleteSensitiveContent(result), System.currentTimeMillis() - start);
        return result;
    }

    /**
     * 判断是否需要记录日志
     */
    private boolean needToLog(Method method) {
        return method.getAnnotation(GetMapping.class) == null
                && !method.getDeclaringClass().equals(GlobalExceptionHandler.class);
    }

    private String getMethodName(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        String SHORT_METHOD_NAME_SUFFIX = "(..)";
        if (methodName.endsWith(SHORT_METHOD_NAME_SUFFIX)) {
            methodName = methodName.substring(0, methodName.length() - SHORT_METHOD_NAME_SUFFIX.length());
        }
        return methodName;
    }

    private String getParamsJson(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            //移除敏感内容
            String paramStr;
            if (arg instanceof HttpServletResponse) {
                paramStr = HttpServletResponse.class.getSimpleName();
            } else if (arg instanceof HttpServletRequest) {
                paramStr = HttpServletRequest.class.getSimpleName();
            } else if (arg instanceof MultipartFile) {
                long size = ((MultipartFile) arg).getSize();
                paramStr = MultipartFile.class.getSimpleName() + " size:" + size;
            } else if (arg instanceof String) {
                paramStr = arg.toString();
            }else {
                paramStr = this.deleteSensitiveContent(arg);
            }
            sb.append(paramStr).append(",");
        }
        return sb.length() > 1 ? sb.deleteCharAt(sb.length() - 1).toString() : sb.toString();
    }

    /**
     * 删除参数中的敏感内容
     * @param obj 参数对象
     * @return 去除敏感内容后的参数对象
     */
    private String deleteSensitiveContent(Object obj) {
        JSONObject jsonObject = new JSONObject();
        if (obj == null || obj instanceof Exception) {
            return jsonObject.toJSONString();
        }

        try {
            if(obj instanceof String || obj instanceof Integer || obj instanceof Long){
                // do nothing
            }else {
                String param = JSON.toJSONString(obj);
                jsonObject = JSONObject.parseObject(param);
                List<String> sensitiveFieldList = this.getSensitiveFieldList();
                for (String sensitiveField : sensitiveFieldList) {
                    if (jsonObject.containsKey(sensitiveField)) {
                        jsonObject.put(sensitiveField, "******");
                    }
                }
            }
        } catch (ClassCastException e) {
            return String.valueOf(obj);
        }
        return jsonObject.toJSONString();
    }

    /**
     * 敏感字段列表（当然这里你可以更改为可配置的）
     */
    private List<String> getSensitiveFieldList() {
        List<String> sensitiveFieldList = Lists.newArrayList();
        sensitiveFieldList.add("pwd");
        sensitiveFieldList.add("password");
        return sensitiveFieldList;
    }
}

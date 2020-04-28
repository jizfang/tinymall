package com.example.tinymall.common.Exceptions;

import com.example.tinymall.common.enums.BusinessExceptionEnum;
import com.example.tinymall.common.enums.ResultCode;
import com.example.tinymall.core.util.StringUtil;
import lombok.Data;

/**
 * @ClassName BusinessException
 * @Description 业务异常类
 * @Author jzf
 * @Date 2020-4-28 16:31
 */
@Data
public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 554846706214629814L;

    protected String code;

    protected String message;

    protected ResultCode resultCode;

    protected Object data;

    public BusinessException() {
        BusinessExceptionEnum exceptionEnum = BusinessExceptionEnum.getByEClass(this.getClass());
        if (exceptionEnum != null) {
            resultCode = exceptionEnum.getResultCode();
            code = exceptionEnum.getResultCode().code().toString();
            message = exceptionEnum.getResultCode().message();
        }

    }

    public BusinessException(String message) {
        this();
        this.message = message;
    }

    public BusinessException(String format, Object... objects) {
        this();
        this.message = StringUtil.formatIfArgs(format, "{}", objects);
    }

    public BusinessException(ResultCode resultCode, Object data) {
        this(resultCode);
        this.data = data;
    }

    public BusinessException(ResultCode resultCode) {
        this.resultCode = resultCode;
        this.code = resultCode.code().toString();
        this.message = resultCode.message();
    }
}

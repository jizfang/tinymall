package com.example.tinymall.common.Exceptions;

import com.example.tinymall.common.enums.ResultCode;

/**
 * @ClassName ParameterInvalidException
 * @Description 参数无效异常
 * @Author jzf
 * @Date 2020-4-28 16:44
 */
public class ParameterInvalidException extends BusinessException{
    private static final long serialVersionUID = -6873445462600706068L;

    public ParameterInvalidException() {
        super();
    }

    public ParameterInvalidException(Object data) {
        super();
        super.data = data;
    }

    public ParameterInvalidException(ResultCode resultCode) {
        super(resultCode);
    }

    public ParameterInvalidException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public ParameterInvalidException(String msg) {
        super(msg);
    }

    public ParameterInvalidException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}

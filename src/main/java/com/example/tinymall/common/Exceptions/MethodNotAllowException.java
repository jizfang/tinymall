package com.example.tinymall.common.Exceptions;

import com.example.tinymall.common.enums.ResultCode;

/**
 * @ClassName MethodNotAllowException
 * @Description 方法不允许异常
 * @Author jzf
 * @Date 2020-4-28 16:43
 */
public class MethodNotAllowException extends BusinessException{
    private static final long serialVersionUID = -7077025842043487858L;

    public MethodNotAllowException() {
        super();
    }

    public MethodNotAllowException(Object data) {
        super.data = data;
    }

    public MethodNotAllowException(ResultCode resultCode) {
        super(resultCode);
    }

    public MethodNotAllowException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public MethodNotAllowException(String msg) {
        super(msg);
    }

    public MethodNotAllowException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}

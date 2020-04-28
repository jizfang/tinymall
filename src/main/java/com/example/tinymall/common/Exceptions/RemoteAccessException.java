package com.example.tinymall.common.Exceptions;

import com.example.tinymall.common.enums.ResultCode;

/**
 * @ClassName RemoteAccessException
 * @Description 远程访问异常
 * @Author jzf
 * @Date 2020-4-28 16:47
 */
public class RemoteAccessException extends BusinessException{
    private static final long serialVersionUID = -3182048300798188620L;

    public RemoteAccessException() {
        super();
    }

    public RemoteAccessException(Object data) {
        super.data = data;
    }

    public RemoteAccessException(ResultCode resultCode) {
        super(resultCode);
    }

    public RemoteAccessException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public RemoteAccessException(String msg) {
        super(msg);
    }

    public RemoteAccessException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}

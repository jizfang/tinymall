package com.example.tinymall.common.Exceptions;

import com.example.tinymall.common.enums.ResultCode;

/**
 * @ClassName DataConflictException
 * @Description 数据已存在异常
 * @Author jzf
 * @Date 2020-4-28 16:34
 */
public class DataConflictException extends BusinessException{
    private static final long serialVersionUID = 2859358836564343784L;

    public DataConflictException() {
        super();
    }

    public DataConflictException(Object data) {
        super.data = data;
    }

    public DataConflictException(ResultCode resultCode) {
        super(resultCode);
    }

    public DataConflictException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public DataConflictException(String msg) {
        super(msg);
    }

    public DataConflictException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}

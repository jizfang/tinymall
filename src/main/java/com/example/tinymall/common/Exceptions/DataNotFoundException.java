package com.example.tinymall.common.Exceptions;

import com.example.tinymall.common.enums.ResultCode;

/**
 * @ClassName DataNotFoundException
 * @Description 数据没有找到异常
 * @Author jzf
 * @Date 2020-4-28 16:42
 */
public class DataNotFoundException extends BusinessException{
    private static final long serialVersionUID = -956486219620951650L;

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(Object data) {
        super();
        super.data = data;
    }

    public DataNotFoundException(ResultCode resultCode) {
        super(resultCode);
    }

    public DataNotFoundException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public DataNotFoundException(String msg) {
        super(msg);
    }

    public DataNotFoundException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}

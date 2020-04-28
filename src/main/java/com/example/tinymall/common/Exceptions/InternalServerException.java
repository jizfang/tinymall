package com.example.tinymall.common.Exceptions;

/**
 * @ClassName InternalServerException
 * @Description 服务器内部错误
 * @Author jzf
 * @Date 2020-4-28 16:43
 */
public class InternalServerException extends BusinessException{
    private static final long serialVersionUID = -2429509355119178412L;

    public InternalServerException() {
        super();
    }

    public InternalServerException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InternalServerException(String msg, Throwable cause, Object... objects) {
        super(msg, cause, objects);
    }

    public InternalServerException(String msg) {
        super(msg);
    }

    public InternalServerException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}

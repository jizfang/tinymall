package com.example.tinymall.common.Exceptions;

/**
 * @ClassName UserNotLoginException
 * @Description 用户未登录异常
 * @Author jzf
 * @Date 2020-4-28 16:48
 */
public class UserNotLoginException extends BusinessException{
    private static final long serialVersionUID = -8216101228643490467L;

    public UserNotLoginException() {
        super();
    }

    public UserNotLoginException(String msg) {
        super(msg);
    }

    public UserNotLoginException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}

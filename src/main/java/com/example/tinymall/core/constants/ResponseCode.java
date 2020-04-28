package com.example.tinymall.core.constants;

public enum ResponseCode {
    SUCCESS(200,"请求成功"),
    FAIL(400,"请求失败"),
    TOKEN_INVALID(401,"用户验证信息无效"),
    NOT_LOGGED_IN(402,"请先登录"),
    USER_TYPE_INVALID(403,"用户类型验证无效"),
    SYSTEM_ERROR(500,"系统繁忙，请稍后再试"),
    SYSTEM_SERIOUS(502,"系统内部错误"),
    USER_NOT_FOUND(1000,"用户不存在"),
    PASSWORD_ERROR(1001,"密码错误"),
    ILLEGAL_ARGUMENT(1002,"参数错误"),
    NO_PERMISSION(1003,"没有权限"),
    BUS_EXCEPTION(99998,"业务异常"),
    SYSTEM_INTERNAL_EXCEPTION(99999,"系统内部异常"),
    ADMIN_INVALID_NAME(601,"无效的用户名"),
    ADMIN_INVALID_PASSWORD(602,"无效的密码"),
    ADMIN_NAME_EXIST(602,"管理员名字已存在"),
    ADMIN_ALTER_NOT_ALLOWED(603,""),
    ADMIN_DELETE_NOT_ALLOWED(604,""),
    ADMIN_INVALID_ACCOUNT(605,""),
    GOODS_UPDATE_NOT_ALLOWED(610,""),
    GOODS_NAME_EXIST(611,""),
    ORDER_CONFIRM_NOT_ALLOWED(620,""),
    ORDER_REFUND_FAILED(621,""),
    ORDER_REPLY_EXIST(622,""),
    ORDER_DELETE_FAILED( 623,""),
    USER_INVALID_NAME(630,""),
    USER_INVALID_PASSWORD(631,""),
    USER_INVALID_MOBILE(632,""),
    USER_NAME_EXIST(633,""),
    USER_MOBILE_EXIST(634,""),
    ROLE_NAME_EXIST(640,""),
    ROLE_SUPER_SUPERMISSION(641,""),
    ROLE_USER_EXIST(642,""),
    GROUPON_GOODS_UNKNOWN(650,""),
    GROUPON_GOODS_EXISTED(651,""),
    GROUPON_GOODS_OFFLINE(652,""),
    NOTICE_UPDATE_NOT_ALLOWED(660,""),
    AFTERSALE_NOT_ALLOWED(670,"");

    private final int msgCode;
    private final String message;

    ResponseCode(int msgCode,String message){
        this.msgCode = msgCode;
        this.message = message;
    }

    public int getMsgCode(){
        return msgCode;
    }
    public String getMessage(){
        return message;
    }
}

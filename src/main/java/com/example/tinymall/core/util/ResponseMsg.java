package com.example.tinymall.core.util;

import com.example.tinymall.core.constants.ResponseCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseMsg<T> implements Serializable {
    private static final long serialVersionUID = -2347812426980715412L;

    private int status;
    private String msg;
    private T data;

    private ResponseMsg(int status) {
        this.status = status;
    }

    private ResponseMsg(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ResponseMsg(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ResponseMsg(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    @JsonIgnore
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getMsgCode();
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static <T> ResponseMsg<T> createBySuccess(){
        return new ResponseMsg<T>(ResponseCode.SUCCESS.getMsgCode());
    }

    public static <T> ResponseMsg<T> createBySuccessMessage(String msg){
        return new ResponseMsg<T>(ResponseCode.SUCCESS.getMsgCode(),msg);
    }

    public static <T> ResponseMsg<T> createBySuccess(T data){
        return new ResponseMsg<T>(ResponseCode.SUCCESS.getMsgCode(),data);
    }

    public static <T> ResponseMsg<T> createBySuccess(String msg, T data){
        return new ResponseMsg<T>(ResponseCode.SUCCESS.getMsgCode(),msg,data);
    }

    public static <T> ResponseMsg<T> createByError(){
        return new ResponseMsg<T>(ResponseCode.FAIL.getMsgCode(),ResponseCode.FAIL.getMessage());
    }

    public static <T> ResponseMsg<T> createByErrorMessage(String errorMessage){
        return new ResponseMsg<T>(ResponseCode.FAIL.getMsgCode(),errorMessage);
    }

    public static <T> ResponseMsg<T> createByErrorCodeMessage(int errorCode, String errorMessage){
        return new ResponseMsg<T>(errorCode,errorMessage);
    }

    public static <T> ResponseMsg<T> badArgument() {
        return new ResponseMsg<T>(ResponseCode.TOKEN_INVALID.getMsgCode(), ResponseCode.TOKEN_INVALID.getMessage());
    }

    public static <T> ResponseMsg<T>  unlogin() {
        return new ResponseMsg<T>(ResponseCode.NOT_LOGGED_IN.getMsgCode(), ResponseCode.NOT_LOGGED_IN.getMessage());
    }

    public static <T> ResponseMsg<T> serious() {
        return new ResponseMsg<T>(ResponseCode.SYSTEM_SERIOUS.getMsgCode(), ResponseCode.SYSTEM_SERIOUS.getMessage());
    }
}


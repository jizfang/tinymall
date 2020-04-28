package com.example.tinymall.common.result;

import com.example.tinymall.common.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CommonResult
 * @Description 通用返回结果
 * @Author jzf
 * @Date 2020-4-28 15:32
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonResult implements Result{
    private static final long serialVersionUID = -8505208681506045353L;

    private Integer code;

    private String msg;

    private Object data;

    public static CommonResult success() {
        CommonResult result = new CommonResult();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static CommonResult success(Object data) {
        CommonResult result = new CommonResult();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static CommonResult failure(ResultCode resultCode) {
        CommonResult result = new CommonResult();
        result.setResultCode(resultCode);
        return result;
    }

    public static CommonResult failure(ResultCode resultCode, Object data) {
        CommonResult result = new CommonResult();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public static CommonResult failure(String message) {
        CommonResult result = new CommonResult();
        result.setCode(ResultCode.PARAM_IS_INVALID.code());
        result.setMsg(message);
        return result;
    }

    private void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }
}

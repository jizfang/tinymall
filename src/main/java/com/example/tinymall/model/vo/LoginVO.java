package com.example.tinymall.model.vo;

import com.example.tinymall.model.bo.LoginUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName LoginVO
 * @Description
 * @Author jzf
 * @Date 2020-4-29 11:32
 */
@ApiModel("登录VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO implements Serializable {
    private static final long serialVersionUID = -3891409116743335997L;

    @ApiModelProperty(value = "用户登陆TOKEN")
    private String token;

    @ApiModelProperty(value = "过期时间（单位：秒）")
    private Long ttl;

    @ApiModelProperty(value = "登陆IP")
    private String ip;

    @ApiModelProperty(value = "登陆平台")
    private String platform;

    @ApiModelProperty(value = "登陆时间")
    private Date loginTime;

    @ApiModelProperty(value = "用户信息")
    private LoginUser user;
}

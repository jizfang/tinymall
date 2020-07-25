package com.example.tinymall.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value="UserLoginInfo", description="用户登录信息")
public class UserLoginInfo {
    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    private String password;
}

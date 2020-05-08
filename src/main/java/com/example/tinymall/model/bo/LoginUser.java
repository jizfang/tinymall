package com.example.tinymall.model.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName LoginUser
 * @Description 登录用户
 * @Author jzf
 * @Date 2020-4-29 9:09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements Serializable {
    private static final long serialVersionUID = 7436474793722359097L;

    @ApiModelProperty(value = "用户ID")
    private Integer id;

    @ApiModelProperty(value = "登陆账号")
    private String nickname;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "最新登陆IP")
    private String latestLoginIp;

    @ApiModelProperty(value = "最新登陆时间")
    private Date latestLoginTime;
}

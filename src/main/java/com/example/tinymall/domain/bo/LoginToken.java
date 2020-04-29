package com.example.tinymall.domain.bo;

import com.example.tinymall.common.enums.CallSourceEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * @ClassName LoginToken
 * @Description 登录的token
 * @Author jzf
 * @Date 2020-4-29 9:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LoginToken{
    @ApiModelProperty(value = "登陆token ID", required = true, position = 0)
    private String id;

    @ApiModelProperty(value = "生存时长(单位：秒)", required = true, position = 1)
    private Long ttl;

    @ApiModelProperty(value = "登录IP", required = true, position = 2)
    private String ip;

    /**
     * 平台 {@link CallSourceEnum}
     */
    @ApiModelProperty(value = "登录平台", required = true, position = 3)
    private String platform;

    @ApiModelProperty(value = "登录时间", required = true, position = 4)
    private Date createTime;

    @ApiModelProperty(value = "登录的用户信息", required = true, position = 6)
    private LoginUser loginUser;
}

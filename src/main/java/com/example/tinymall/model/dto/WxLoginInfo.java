package com.example.tinymall.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName WxLoginInfo
 * @Description
 * @Author jzf
 * @Date 2020-4-14 19:56
 */
@Getter
@Setter
public class WxLoginInfo {
    private String code;
    private UserInfo userInfo;
}

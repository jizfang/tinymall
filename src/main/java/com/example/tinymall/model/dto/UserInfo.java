package com.example.tinymall.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName UserInfo
 * @Description
 * @Author jzf
 * @Date 2020-4-14 19:57
 */
@Setter
@Getter
public class UserInfo {
    private String name;
    private String avatarUrl;
    private String country;
    private String province;
    private String city;
    private String language;
    private Byte gender;
    private Integer[] roles;
}

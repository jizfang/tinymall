package com.example.tinymall.model.bo;

import lombok.Data;

@Data
public class RegisterInfo {
    private String username;
    private String password;
    private String mobile;
    private String wxCode;
}

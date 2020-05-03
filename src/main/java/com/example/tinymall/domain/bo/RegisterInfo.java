package com.example.tinymall.domain.bo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RegisterInfo {
    private String username;
    private String password;
    private String mobile;
    private String wxCode;
}

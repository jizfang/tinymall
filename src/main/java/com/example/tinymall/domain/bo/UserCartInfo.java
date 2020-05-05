package com.example.tinymall.domain.bo;

import lombok.Data;

@Data
public class UserCartInfo {
    private Integer cartId;
    private Integer addressId;
    private Integer couponId;
    private Integer userCouponId;
    private String message;
    private Integer grouponRulesId;
    private Integer grouponLinkId;
}

package com.example.tinymall.service;

import com.example.tinymall.entity.TinymallCoupon;

import java.math.BigDecimal;

/**
 * @ClassName CouponVerifyService
 * @Description
 * @Author jzf
 * @Date 2020-4-23 10:35
 */
public interface CouponVerifyService {
    TinymallCoupon checkCoupon(Integer userId, Integer couponId, Integer userCouponId, BigDecimal checkedGoodsPrice);
}

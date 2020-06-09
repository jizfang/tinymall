package com.example.tinymall.service.impl;

import com.example.tinymall.core.constants.CouponConstant;
import com.example.tinymall.entity.TinymallCoupon;
import com.example.tinymall.entity.TinymallCouponUser;
import com.example.tinymall.service.CouponVerifyService;
import com.example.tinymall.service.TinymallCouponService;
import com.example.tinymall.service.TinymallCouponUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @ClassName CouponVerifyServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-23 10:36
 */
@Service
public class CouponVerifyServiceImpl implements CouponVerifyService {

    @Autowired
    private TinymallCouponUserService couponUserService;
    @Autowired
    private TinymallCouponService couponService;

    @Override
    public TinymallCoupon checkCoupon(Integer userId, Integer couponId, Integer userCouponId, BigDecimal checkedGoodsPrice) {
        TinymallCoupon coupon = couponService.selectByPk(couponId);
        if (coupon == null) {
            return null;
        }

        TinymallCouponUser couponUser = couponUserService.findById(userCouponId);
        if (couponUser == null) {
            //couponUser = couponUserService.queryOne(userId, couponId);
        } else if (!couponId.equals(couponUser.getCouponId())) {
            return null;
        }

        if (couponUser == null) {
            return null;
        }

        // 检查是否超期
        Short timeType = coupon.getTimeType();
        Short days = coupon.getDays();
        LocalDateTime now = LocalDateTime.now();
        if (timeType.equals(CouponConstant.TIME_TYPE_TIME)) {
            if (now.isBefore(coupon.getStartTime()) || now.isAfter(coupon.getEndTime())) {
                return null;
            }
        } else if (timeType.equals(CouponConstant.TIME_TYPE_DAYS)) {
            LocalDateTime expired = couponUser.getAddTime().plusDays(days);
            if (now.isAfter(expired)) {
                return null;
            }
        } else {
            return null;
        }

        // 检测商品是否符合
        // TODO 目前仅支持全平台商品，所以不需要检测
        Short goodType = coupon.getGoodsType();
        if (!goodType.equals(CouponConstant.GOODS_TYPE_ALL)) {
            return null;
        }

        // 检测订单状态
        Short status = coupon.getStatus();
        if (!status.equals(CouponConstant.STATUS_NORMAL)) {
            return null;
        }
        // 检测是否满足最低消费
        if (checkedGoodsPrice.compareTo(coupon.getMin()) == -1) {
            return null;
        }

        return coupon;
    }
}

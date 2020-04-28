package com.example.tinymall.service.impl;

import com.example.tinymall.core.constants.CouponConstant;
import com.example.tinymall.domain.TinymallCoupon;
import com.example.tinymall.domain.TinymallCouponUser;
import com.example.tinymall.service.CouponAssignService;
import com.example.tinymall.service.TinymallCouponService;
import com.example.tinymall.service.TinymallCouponUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName CouponAssignServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-14 20:12
 */
@Service
public class CouponAssignServiceImpl implements CouponAssignService {

    @Autowired
    private TinymallCouponUserService couponUserService;
    @Autowired
    private TinymallCouponService couponService;

    @Override
    public void assignForRegister(Integer userId) {
        List<TinymallCoupon> couponList = couponService.queryRegister();
        for (TinymallCoupon coupon : couponList) {
            Integer couponId = coupon.getId();

            Integer count = couponUserService.countUserAndCoupon(userId, couponId);
            if (count > 0) {
                continue;
            }

            Short limit = coupon.getLimit();
            while (limit > 0) {
                TinymallCouponUser couponUser = new TinymallCouponUser();
                couponUser.setCouponId(couponId);
                couponUser.setUserId(userId);
                Short timeType = coupon.getTimeType();
                if (timeType.equals(CouponConstant.TIME_TYPE_TIME)) {
                    couponUser.setStartTime(coupon.getStartTime());
                    couponUser.setEndTime(coupon.getEndTime());
                } else {
                    LocalDateTime now = LocalDateTime.now();
                    couponUser.setStartTime(now);
                    couponUser.setEndTime(now.plusDays(coupon.getDays()));
                }
                couponUserService.add(couponUser);

                limit--;
            }
        }
    }
}

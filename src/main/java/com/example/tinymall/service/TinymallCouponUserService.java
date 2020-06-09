package com.example.tinymall.service;

import com.example.tinymall.entity.TinymallCouponUser;
import com.example.tinymall.model.vo.CouponVo;

import java.util.List;

/**
 * @ClassName TinymallCouponUserService
 * @Description
 * @Author jzf
 * @Date 2020-4-14 20:14
 */
public interface TinymallCouponUserService {
    Integer countCoupon(Integer couponId);

    Integer countUserAndCoupon(Integer userId, Integer couponId);

    void add(TinymallCouponUser couponUser);

    List<CouponVo> queryList(TinymallCouponUser condition);

    List<CouponVo> queryAll(Integer userId);
    CouponVo queryOne(Integer userId, Integer couponId);

    TinymallCouponUser findById(Integer id);

    int update(TinymallCouponUser couponUser);

    List<TinymallCouponUser> queryExpired();
}

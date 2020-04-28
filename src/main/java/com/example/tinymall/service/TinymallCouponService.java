package com.example.tinymall.service;

import com.example.tinymall.domain.TinymallCoupon;
import com.example.tinymall.domain.TinymallCouponExample;

import java.util.List;

/**
 * @ClassName TinymallCouponService
 * @Description
 * @Author jzf
 * @Date 2020-4-10 16:10
 */
public interface TinymallCouponService {
    List queryList(int offset, int limit);

    List<TinymallCoupon> queryList(TinymallCouponExample.Criteria criteria, int offset, int limit, String sort, String order);

    List<TinymallCoupon> queryList(int offset, int limit, String sort, String order);

    List<TinymallCoupon> queryRegister();

    TinymallCoupon findById(Integer couponId);
}

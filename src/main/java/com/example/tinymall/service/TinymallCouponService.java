package com.example.tinymall.service;

import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.entity.TinymallCoupon;

import java.util.List;

/**
 * @ClassName TinymallCouponService
 * @Description
 * @Author jzf
 * @Date 2020-4-10 16:10
 */
public interface TinymallCouponService {

    PageVO<TinymallCoupon> queryList(PageQO pageQO);

    List<TinymallCoupon> queryRegister();

    TinymallCoupon findById(Integer couponId);
}

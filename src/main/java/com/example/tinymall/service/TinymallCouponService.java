package com.example.tinymall.service;

import com.example.tinymall.common.mineservice.BaseService;
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
public interface TinymallCouponService extends BaseService<TinymallCoupon,Integer> {

    List<TinymallCoupon> queryRegister();
}

package com.example.tinymall.service;

import com.example.tinymall.domain.TinymallOrder;

/**
 * @ClassName TinymallOrderService
 * @Description
 * @Author jzf
 * @Date 2020-4-11 17:44
 */
public interface TinymallOrderService {

    Object orderInfo(Integer userId);

    String generateOrderSn(Integer userId);

    int add(TinymallOrder order);

    TinymallOrder findById(Integer userId, Integer orderId);

    TinymallOrder findById(Integer orderId);
}

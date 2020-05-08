package com.example.tinymall.service;

import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.entity.TinymallOrder;

import java.util.List;

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

    PageVO<TinymallOrder> queryByOrderStatus(Integer userId, List<Short> orderStatus, Integer page, Integer limit, String orderBy);
}

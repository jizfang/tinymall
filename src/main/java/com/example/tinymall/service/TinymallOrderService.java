package com.example.tinymall.service;

import com.example.tinymall.common.mineservice.BaseService;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.entity.TinymallOrder;

import java.util.List;

/**
 * @ClassName TinymallOrderService
 * @Description
 * @Author jzf
 * @Date 2020-4-11 17:44
 */
public interface TinymallOrderService extends BaseService<TinymallOrder,Integer> {

    Object orderInfo(Integer userId);

    String generateOrderSn(Integer userId);

    TinymallOrder findById(Integer userId, Integer orderId);

    PageVO<TinymallOrder> queryByOrderStatus(Integer userId, List<Short> orderStatus, Integer page, Integer limit, String orderBy);
}

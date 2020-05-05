package com.example.tinymall.service;

import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.domain.TinymallOrder;
import com.example.tinymall.domain.bo.OrderInfo;
import com.example.tinymall.domain.bo.UserCartInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName WxOrderService
 * @Description
 * @Author jzf
 * @Date 2020-4-23 11:02
 */
public interface WxOrderService {
    Object submit(Integer userId, UserCartInfo userCartInfo);

    Object prepay(Integer userId, OrderInfo orderInfo, HttpServletRequest request);

    PageVO<TinymallOrder> list(PageQO pageQO);

    Object detail(Integer userId, Integer orderId);
}

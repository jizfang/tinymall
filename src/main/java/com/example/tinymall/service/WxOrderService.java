package com.example.tinymall.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName WxOrderService
 * @Description
 * @Author jzf
 * @Date 2020-4-23 11:02
 */
public interface WxOrderService {
    Object submit(Integer userId, String body);

    Object prepay(Integer userId, String body, HttpServletRequest request);
}

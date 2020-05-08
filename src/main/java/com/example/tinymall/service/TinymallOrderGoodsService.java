package com.example.tinymall.service;

import com.example.tinymall.entity.TinymallOrderGoods;

import java.util.List;

/**
 * @ClassName LitemallOrderGoodsService
 * @Description
 * @Author jzf
 * @Date 2020-4-23 11:23
 */
public interface TinymallOrderGoodsService {
    int add(TinymallOrderGoods orderGoods);
    List<TinymallOrderGoods> queryByOid(Integer orderId);
    List<TinymallOrderGoods> findByOidAndGid(Integer orderId, Integer goodsId);
    TinymallOrderGoods findById(Integer id);
    void updateById(TinymallOrderGoods orderGoods);
    Short getComments(Integer orderId);
    boolean checkExist(Integer goodsId);
    void deleteByOrderId(Integer orderId);
}

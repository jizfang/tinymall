package com.example.tinymall.service;

import com.example.tinymall.domain.TinymallGoodsProduct;

import java.util.List;

/**
 * @ClassName TinymallGoodsProductService
 * @Description
 * @Author jzf
 * @Date 2020-4-15 9:48
 */
public interface TinymallGoodsProductService {
    List<TinymallGoodsProduct> queryByGid(Integer gid);

    TinymallGoodsProduct findById(Integer id);

    void deleteById(Integer id);

    void add(TinymallGoodsProduct goodsProduct);

    int count();

    void deleteByGid(Integer gid);

    int addStock(Integer id, Short num);

    int reduceStock(Integer id, Short num);

    void updateById(TinymallGoodsProduct product);
}

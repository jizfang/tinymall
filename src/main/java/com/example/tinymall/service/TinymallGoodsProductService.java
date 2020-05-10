package com.example.tinymall.service;

import com.example.tinymall.common.mineservice.BaseService;
import com.example.tinymall.entity.TinymallGoodsProduct;

import java.util.List;

/**
 * @ClassName TinymallGoodsProductService
 * @Description
 * @Author jzf
 * @Date 2020-4-15 9:48
 */
public interface TinymallGoodsProductService extends BaseService<TinymallGoodsProduct,Integer> {
    List<TinymallGoodsProduct> queryByGid(Integer gid);

    int count();

    void deleteByGid(Integer gid);

    int addStock(Integer id, Short num);

    int reduceStock(Integer id, Short num);
}

package com.example.tinymall.service;

import com.example.tinymall.domain.TinymallGoods;

import java.util.List;

/**
 * @ClassName TinymallGoodsService
 * @Description
 * @Author jzf
 * @Date 2020-4-10 16:09
 */
public interface TinymallGoodsService {
    List<TinymallGoods> queryByNew(int offset, int limit);

    List<TinymallGoods> queryByHot(int offset, int limit);

    List<TinymallGoods> queryByCategory(List<Integer> l2List, int i, Integer catlogMoreLimit);

    List<TinymallGoods> queryByCategory(Integer catId, int offset, int limit);

    Integer queryOnSale();

    List<TinymallGoods> querySelective(Integer categoryId, Integer brandId, String keyword, Boolean isHot, Boolean isNew, Integer page, Integer limit, String sort, String order);

    List<Integer> getCatIds(Integer brandId, String keyword, Boolean isHot, Boolean isNew);

    TinymallGoods findById(Integer id);
}

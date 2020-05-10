package com.example.tinymall.service;

import com.example.tinymall.common.mineservice.BaseService;
import com.example.tinymall.entity.TinymallGoodsAttribute;

import java.util.List;

/**
 * @ClassName TinymallGoodsAttributeService
 * @Description
 * @Author jzf
 *
 * @Date 2020-4-14 21:01
 */
public interface TinymallGoodsAttributeService extends BaseService<TinymallGoodsAttribute,Integer> {

    List queryByGid(Integer goodsId);
}

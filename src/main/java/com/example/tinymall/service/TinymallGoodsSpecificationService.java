package com.example.tinymall.service;

import com.example.tinymall.entity.TinymallGoodsSpecification;

import java.util.List;

/**
 * @ClassName LitemallGoodsSpecificationService
 * @Description
 * @Author jzf
 * @Date 2020-4-14 21:05
 */
public interface TinymallGoodsSpecificationService {
    List<TinymallGoodsSpecification> queryByGid(Integer id);

    TinymallGoodsSpecification findById(Integer id);

    void deleteByGid(Integer gid);

    void add(TinymallGoodsSpecification goodsSpecification);

    Object getSpecificationVoList(Integer id);

    void updateById(TinymallGoodsSpecification specification);
}

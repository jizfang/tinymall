package com.example.tinymall.service;

import com.example.tinymall.entity.TinymallFootprint;

import java.util.List;

/**
 * @ClassName TinymallFootprintService
 * @Description
 * @Author jzf
 * @Date 2020-4-15 12:04
 */
public interface TinymallFootprintService {
    List<TinymallFootprint> queryByAddTime(Integer userId, Integer page, Integer size);

    TinymallFootprint findById(Integer id);

    TinymallFootprint findById(Integer userId, Integer id);

    void deleteById(Integer id);

    void add(TinymallFootprint footprint);

    List<TinymallFootprint> querySelective(String userId, String goodsId, Integer page, Integer size, String sort, String order);
}

package com.example.tinymall.service;

import com.example.tinymall.domain.TinymallAd;

import java.util.List;

/**
 * @ClassName TinymallAdService
 * @Description
 * @Author jzf
 * @Date 2020-4-10 10:33
 */
public interface TinymallAdService {

    List<TinymallAd> queryIndex();

    List<TinymallAd> querySelective(String name, String content, Integer page, Integer limit, String sort, String order);

    int updateById(TinymallAd ad);

    void deleteById(Integer id);

    void add(TinymallAd ad);

    TinymallAd findById(Integer id);
}

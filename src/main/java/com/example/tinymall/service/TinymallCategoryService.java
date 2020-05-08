package com.example.tinymall.service;

import com.example.tinymall.entity.TinymallCategory;

import java.util.List;

/**
 * @ClassName TinymallCategoryService
 * @Description
 * @Author jzf
 * @Date 2020-4-10 16:09
 */
public interface TinymallCategoryService {
    List<TinymallCategory> queryChannel();

    List<TinymallCategory> queryL1WithoutRecommend(int offset, int limit);

    List<TinymallCategory> queryByPid(Integer pid);

    List<TinymallCategory> queryL1();

    TinymallCategory findById(Integer id);

    List<TinymallCategory> queryL2ByIds(List<Integer> ids);
}

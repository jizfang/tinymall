package com.example.tinymall.service;

import com.example.tinymall.domain.TinymallBrand;

import java.util.List;

/**
 * @ClassName TinymallBrandService
 * @Description
 * @Author jzf
 * @Date 2020-4-10 16:09
 */
public interface TinymallBrandService {
    List<TinymallBrand> query(Integer page, Integer limit);

    List<TinymallBrand> query(Integer page, Integer limit, String sort, String order);

    TinymallBrand findById(Integer brandId);
}

package com.example.tinymall.service;

import com.example.tinymall.domain.TinymallStorage;
import com.example.tinymall.domain.TinymallStorageExample;

import java.util.List;

/**
 * @ClassName TinymallStorageService
 * @Description
 * @Author jzf
 * @Date 2020-4-9 17:03
 */
public interface TinymallStorageService {
    void deleteByKey(String key);

    void add(TinymallStorage storageInfo);

    TinymallStorage findByKey(String key);

    int update(TinymallStorage storageInfo);

    TinymallStorage findById(Integer id);

    List<TinymallStorage> querySelective(String key, String name, Integer page, Integer limit, String sort, String order);
}

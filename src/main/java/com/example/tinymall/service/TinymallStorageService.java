package com.example.tinymall.service;

import com.example.tinymall.common.mineservice.BaseService;
import com.example.tinymall.entity.TinymallStorage;

import java.util.List;

/**
 * @ClassName TinymallStorageService
 * @Description
 * @Author jzf
 * @Date 2020-4-9 17:03
 */
public interface TinymallStorageService extends BaseService<TinymallStorage,Integer> {

    TinymallStorage findByKey(String key);

    void deleteByPk(String key);
}

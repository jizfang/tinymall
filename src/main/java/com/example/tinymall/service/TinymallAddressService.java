package com.example.tinymall.service;

import com.example.tinymall.common.mineservice.BaseService;
import com.example.tinymall.entity.TinymallAddress;

import java.util.List;

/**
 * @ClassName TinymallAddressService
 * @Description
 * @Author jzf
 * @Date 2020-4-15 20:11
 */
public interface TinymallAddressService extends BaseService<TinymallAddress,Integer> {
    List<TinymallAddress> queryByUid(Integer uid);

    TinymallAddress query(Integer userId, Integer id);

    TinymallAddress findDefault(Integer userId);

    void resetDefault(Integer userId);
}

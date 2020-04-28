package com.example.tinymall.service;

import com.example.tinymall.domain.TinymallAddress;

import java.util.List;

/**
 * @ClassName TinymallAddressService
 * @Description
 * @Author jzf
 * @Date 2020-4-15 20:11
 */
public interface TinymallAddressService {
    List<TinymallAddress> queryByUid(Integer uid);

    TinymallAddress query(Integer userId, Integer id);

    int add(TinymallAddress address);

    int update(TinymallAddress address);

    void delete(Integer id);

    TinymallAddress findDefault(Integer userId);

    void resetDefault(Integer userId);

    List<TinymallAddress> querySelective(Integer userId, String name, Integer page, Integer limit, String sort, String order);
}

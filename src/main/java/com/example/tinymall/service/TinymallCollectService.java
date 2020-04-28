package com.example.tinymall.service;

import com.example.tinymall.domain.TinymallCollect;

import java.util.List;

/**
 * @ClassName LitemallCollectService
 * @Description
 * @Author jzf
 * @Date 2020-4-15 11:58
 */
public interface TinymallCollectService {
    int count(int uid, Integer gid);

    List<TinymallCollect> queryByType(Integer userId, Byte type, Integer page, Integer limit, String sort, String order);

    int countByType(Integer userId, Byte type);

    TinymallCollect queryByTypeAndValue(Integer userId, Byte type, Integer valueId);

    void deleteById(Integer id);

    int add(TinymallCollect collect);

    List<TinymallCollect> querySelective(String userId, String valueId, Integer page, Integer size, String sort, String order);
}

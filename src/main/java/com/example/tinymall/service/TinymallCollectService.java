package com.example.tinymall.service;

import com.example.tinymall.common.mineservice.BaseService;
import com.example.tinymall.entity.TinymallCollect;

import java.util.List;

/**
 * @ClassName TinymallCollectService
 * @Description
 * @Author jzf
 * @Date 2020-4-15 11:58
 */
public interface TinymallCollectService extends BaseService<TinymallCollect,Integer> {
    int count(int uid, Integer gid);

    List<TinymallCollect> queryByType(Integer userId, Byte type, Integer page, Integer limit, String sort, String order);

    int countByType(Integer userId, Byte type);

    TinymallCollect queryByTypeAndValue(Integer userId, Byte type, Integer valueId);
}

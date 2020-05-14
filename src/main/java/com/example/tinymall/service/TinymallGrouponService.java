package com.example.tinymall.service;

import com.example.tinymall.common.mineservice.BaseService;
import com.example.tinymall.entity.TinymallGroupon;

import java.util.List;

/**
 * @ClassName TinymallGrouponService
 * @Description
 * @Author jzf
 * @Date 2020-4-23 11:08
 */
public interface TinymallGrouponService extends BaseService<TinymallGroupon,Integer> {
    List<TinymallGroupon> queryMyGroupon(Integer userId);
    List<TinymallGroupon> queryMyJoinGroupon(Integer userId);
    TinymallGroupon queryByOrderId(Integer orderId);
    List<TinymallGroupon> queryJoinRecord(Integer id);
    TinymallGroupon queryById(Integer userId, Integer id);
    int countGroupon(Integer grouponId);
    boolean hasJoin(Integer userId, Integer grouponId);
}

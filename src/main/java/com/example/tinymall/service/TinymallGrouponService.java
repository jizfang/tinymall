package com.example.tinymall.service;

import com.example.tinymall.entity.TinymallGroupon;

import java.util.List;

/**
 * @ClassName TinymallGrouponService
 * @Description
 * @Author jzf
 * @Date 2020-4-23 11:08
 */
public interface TinymallGrouponService {
    List<TinymallGroupon> queryMyGroupon(Integer userId);
    List<TinymallGroupon> queryMyJoinGroupon(Integer userId);
    TinymallGroupon queryByOrderId(Integer orderId);
    List<TinymallGroupon> queryJoinRecord(Integer id);
    TinymallGroupon queryById(Integer id);
    TinymallGroupon queryById(Integer userId, Integer id);
    int countGroupon(Integer grouponId);
    boolean hasJoin(Integer userId, Integer grouponId);
    int updateById(TinymallGroupon groupon);
    int createGroupon(TinymallGroupon groupon);
    List<TinymallGroupon> querySelective(String rulesId, Integer page, Integer size, String sort, String order);
    List<TinymallGroupon> queryByRuleId(int grouponRuleId);
}

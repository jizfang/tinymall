package com.example.tinymall.service;

import com.example.tinymall.entity.TinymallGrouponRules;

import java.util.List;

/**
 * @ClassName WxGrouponRuleService
 * @Description
 * @Author jzf
 * @Date 2020-4-10 16:09
 */
public interface WxGrouponRuleService {
    List<TinymallGrouponRules> queryList(Integer page, Integer limit);

    List<TinymallGrouponRules> queryList(Integer page, Integer limit, String sort, String order);
}

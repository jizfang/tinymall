package com.example.tinymall.service.impl;

import com.example.tinymall.core.constants.GrouponConstant;
import com.example.tinymall.entity.TinymallGrouponRules;
import com.example.tinymall.mapper.TinymallGrouponRulesMapper;
import com.example.tinymall.service.WxGrouponRuleService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName WxGrouponRuleServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-10 16:17
 */
@Service
@Primary
public class WxGrouponRuleServiceImpl implements WxGrouponRuleService {

    @Autowired
    private TinymallGrouponRulesMapper mapper;

    @Override
    public List<TinymallGrouponRules> queryList(Integer page, Integer limit) {
        return queryList(page, limit, "add_time", "desc");
    }

    @Override
    public List<TinymallGrouponRules> queryList(Integer page, Integer limit, String sort, String order) {
        /*TinymallGrouponRulesExample example = new TinymallGrouponRulesExample();
        example.or().andStatusEqualTo(GrouponConstant.RULE_STATUS_ON).andDeletedEqualTo(false);
        example.setOrderByClause(sort + " " + order);
        PageHelper.startPage(page, limit);
        return mapper.selectByExample(example);*/
        return Lists.newArrayList();
    }
}

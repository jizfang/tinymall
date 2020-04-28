package com.example.tinymall.service.impl;

import com.example.tinymall.core.constants.GrouponConstant;
import com.example.tinymall.dao.TinymallGrouponRulesMapper;
import com.example.tinymall.domain.TinymallGrouponRules;
import com.example.tinymall.domain.TinymallGrouponRulesExample;
import com.example.tinymall.service.WxGrouponRuleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName WxGrouponRuleServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-10 16:17
 */
@Service
public class WxGrouponRuleServiceImpl implements WxGrouponRuleService {

    @Autowired
    private TinymallGrouponRulesMapper mapper;

    @Override
    public List<TinymallGrouponRules> queryList(Integer page, Integer limit) {
        return queryList(page, limit, "add_time", "desc");
    }

    @Override
    public List<TinymallGrouponRules> queryList(Integer page, Integer limit, String sort, String order) {
        TinymallGrouponRulesExample example = new TinymallGrouponRulesExample();
        example.or().andStatusEqualTo(GrouponConstant.RULE_STATUS_ON).andDeletedEqualTo(false);
        example.setOrderByClause(sort + " " + order);
        PageHelper.startPage(page, limit);
        return mapper.selectByExample(example);
    }
}

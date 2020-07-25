package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.core.constants.GrouponConstant;
import com.example.tinymall.entity.TinymallGrouponRules;
import com.example.tinymall.mapper.TinymallGoodsMapper;
import com.example.tinymall.mapper.TinymallGrouponRulesMapper;
import com.example.tinymall.service.TinymallGrouponRulesService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName TinymallGrouponRulesServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-15 11:55
 */
@Service
@Primary
public class TinymallGrouponRulesServiceImpl extends BaseMySqlServiceImpl<TinymallGrouponRules,Integer> implements TinymallGrouponRulesService {
    @Resource
    private TinymallGrouponRulesMapper mapper;
    @Resource
    private TinymallGoodsMapper goodsMapper;

    @Override
    public TinymallGrouponRules findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TinymallGrouponRules> queryByGoodsId(Integer goodsId) {
        TinymallGrouponRules tinymallGrouponRules = new TinymallGrouponRules();
        tinymallGrouponRules.setGoodsId(goodsId);
        tinymallGrouponRules.setStatus(GrouponConstant.RULE_STATUS_ON);
        tinymallGrouponRules.setDeleted(0);
        return mapper.select(tinymallGrouponRules);
    }

    @Override
    public int countByGoodsId(Integer goodsId) {
//        TinymallGrouponRulesExample example = new TinymallGrouponRulesExample();
//        example.or().andGoodsIdEqualTo(goodsId).andStatusEqualTo(GrouponConstant.RULE_STATUS_ON).andDeletedEqualTo(false);
//        return (int) mapper.countByExample(example);
        return 0;
    }

    @Override
    public List<TinymallGrouponRules> queryByStatus(Short status) {
        /*TinymallGrouponRulesExample example = new TinymallGrouponRulesExample();
        example.or().andStatusEqualTo(status).andDeletedEqualTo(false);
        return mapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

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

    @Override
    public boolean isExpired(TinymallGrouponRules rules) {
        return (rules == null || rules.getExpireTime().isBefore(LocalDateTime.now()));
    }

    @Override
    public List<TinymallGrouponRules> querySelective(String goodsId, Integer page, Integer size, String sort, String order) {
        /*TinymallGrouponRulesExample example = new TinymallGrouponRulesExample();
        example.setOrderByClause(sort + " " + order);

        TinymallGrouponRulesExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(goodsId)) {
            criteria.andGoodsIdEqualTo(Integer.parseInt(goodsId));
        }
        criteria.andDeletedEqualTo(false);

        PageHelper.startPage(page, size);
        return mapper.selectByExample(example);*/
        return Lists.newArrayList();
    }
}

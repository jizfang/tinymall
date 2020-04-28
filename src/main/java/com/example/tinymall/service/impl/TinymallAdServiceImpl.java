package com.example.tinymall.service.impl;

import com.example.tinymall.dao.TinymallAdMapper;
import com.example.tinymall.domain.TinymallAd;
import com.example.tinymall.domain.TinymallAdExample;
import com.example.tinymall.service.TinymallAdService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TinymallAdServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-10 10:35
 */
@Service
@Slf4j
public class TinymallAdServiceImpl implements TinymallAdService {

    @Autowired
    private TinymallAdMapper tinymallAdMapper;

    @Override
    public List<TinymallAd> queryIndex() {
        TinymallAdExample example = new TinymallAdExample();
        example.or().andPositionEqualTo((byte) 1).andDeletedEqualTo(false).andEnabledEqualTo(true);
        return tinymallAdMapper.selectByExample(example);
    }

    @Override
    public List<TinymallAd> querySelective(String name, String content, Integer page, Integer limit, String sort, String order) {
        TinymallAdExample example = new TinymallAdExample();
        TinymallAdExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(content)) {
            criteria.andContentLike("%" + content + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return tinymallAdMapper.selectByExample(example);
    }

    @Override
    public int updateById(TinymallAd ad) {
        return 0;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void add(TinymallAd ad) {

    }

    @Override
    public TinymallAd findById(Integer id) {
        return null;
    }
}

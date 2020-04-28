package com.example.tinymall.service.impl;

import com.example.tinymall.dao.TinymallCollectMapper;
import com.example.tinymall.domain.TinymallCollect;
import com.example.tinymall.domain.TinymallCollectExample;
import com.example.tinymall.service.TinymallCollectService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName TinymallCollectServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-15 12:00
 */
@Service
public class TinymallCollectServiceImpl implements TinymallCollectService {
    @Resource
    private TinymallCollectMapper collectMapper;

    @Override
    public int count(int uid, Integer gid) {
        TinymallCollectExample example = new TinymallCollectExample();
        example.or().andUserIdEqualTo(uid).andValueIdEqualTo(gid).andDeletedEqualTo(false);
        return (int) collectMapper.countByExample(example);
    }

    @Override
    public List<TinymallCollect> queryByType(Integer userId, Byte type, Integer page, Integer limit, String sort, String order) {
        TinymallCollectExample example = new TinymallCollectExample();
        TinymallCollectExample.Criteria criteria = example.createCriteria();

        if (type != null) {
            criteria.andTypeEqualTo(type);
        }
        criteria.andUserIdEqualTo(userId);
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return collectMapper.selectByExample(example);
    }

    @Override
    public int countByType(Integer userId, Byte type) {
        TinymallCollectExample example = new TinymallCollectExample();
        example.or().andUserIdEqualTo(userId).andTypeEqualTo(type).andDeletedEqualTo(false);
        return (int) collectMapper.countByExample(example);
    }

    @Override
    public TinymallCollect queryByTypeAndValue(Integer userId, Byte type, Integer valueId) {
        TinymallCollectExample example = new TinymallCollectExample();
        example.or().andUserIdEqualTo(userId).andValueIdEqualTo(valueId).andTypeEqualTo(type).andDeletedEqualTo(false);
        return collectMapper.selectOneByExample(example);
    }

    @Override
    public void deleteById(Integer id) {
        collectMapper.logicalDeleteByPrimaryKey(id);
    }

    @Override
    public int add(TinymallCollect collect) {
        collect.setAddTime(LocalDateTime.now());
        collect.setUpdateTime(LocalDateTime.now());
        return collectMapper.insertSelective(collect);
    }

    @Override
    public List<TinymallCollect> querySelective(String userId, String valueId, Integer page, Integer size, String sort, String order) {
        TinymallCollectExample example = new TinymallCollectExample();
        TinymallCollectExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(valueId)) {
            criteria.andValueIdEqualTo(Integer.valueOf(valueId));
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return collectMapper.selectByExample(example);
    }
}

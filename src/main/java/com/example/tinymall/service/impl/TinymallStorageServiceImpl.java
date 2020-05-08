package com.example.tinymall.service.impl;

import com.example.tinymall.entity.TinymallStorage;
import com.example.tinymall.mapper.TinymallStorageMapper;
import com.example.tinymall.service.TinymallStorageService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName TinymallStorageServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-9 17:06
 */
@Service
public class TinymallStorageServiceImpl implements TinymallStorageService {
    @Autowired
    private TinymallStorageMapper storageMapper;

    @Override
    public void deleteByKey(String key) {
        /*TinymallStorageExample example = new TinymallStorageExample();
        example.or().andKeyEqualTo(key);
        storageMapper.logicalDeleteByExample(example);*/
    }

    @Override
    public void add(TinymallStorage storageInfo) {
        storageInfo.setAddTime(LocalDateTime.now());
        storageInfo.setUpdateTime(LocalDateTime.now());
        storageMapper.insertSelective(storageInfo);
    }

    @Override
    public TinymallStorage findByKey(String key) {
        /*TinymallStorageExample example = new TinymallStorageExample();
        example.or().andKeyEqualTo(key).andDeletedEqualTo(false);
        return storageMapper.selectOneByExample(example);*/
        return null;
    }

    @Override
    public int update(TinymallStorage storageInfo) {
        storageInfo.setUpdateTime(LocalDateTime.now());
        return storageMapper.updateByPrimaryKeySelective(storageInfo);
    }

    @Override
    public TinymallStorage findById(Integer id) {
        return storageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TinymallStorage> querySelective(String key, String name, Integer page, Integer limit, String sort, String order) {
        /*TinymallStorageExample example = new TinymallStorageExample();
        TinymallStorageExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(key)) {
            criteria.andKeyEqualTo(key);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return storageMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }
}

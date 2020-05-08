package com.example.tinymall.service.impl;

import com.example.tinymall.entity.TinymallCategory;
import com.example.tinymall.mapper.TinymallCategoryMapper;
import com.example.tinymall.service.TinymallCategoryService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TinymallCategoryService
 * @Description
 * @Author jzf
 * @Date 2020-4-11 10:34
 */
@Service
public class TinymallCategoryServiceImpl implements TinymallCategoryService {

    @Resource
    private TinymallCategoryMapper categoryMapper;

    @Override
    public List<TinymallCategory> queryChannel() {
        /*TinymallCategoryExample example = new TinymallCategoryExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        return categoryMapper.selectByExampleSelective(example, CHANNEL);*/
        return Lists.newArrayList();
    }

    @Override
    public List<TinymallCategory> queryL1WithoutRecommend(int offset, int limit) {
        /*TinymallCategoryExample example = new TinymallCategoryExample();
        example.or().andLevelEqualTo("L1").andNameNotEqualTo("推荐").andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return categoryMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public List<TinymallCategory> queryByPid(Integer pid) {
        /*TinymallCategoryExample example = new TinymallCategoryExample();
        example.or().andPidEqualTo(pid).andDeletedEqualTo(false);
        return categoryMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public List<TinymallCategory> queryL1() {
        /*TinymallCategoryExample example = new TinymallCategoryExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        return categoryMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public TinymallCategory findById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TinymallCategory> queryL2ByIds(List<Integer> ids) {
        /*TinymallCategoryExample example = new TinymallCategoryExample();
        example.or().andIdIn(ids).andLevelEqualTo("L2").andDeletedEqualTo(false);
        return categoryMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }
}

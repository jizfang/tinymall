package com.example.tinymall.service.impl;

import com.example.tinymall.entity.TinymallBrand;
import com.example.tinymall.mapper.TinymallBrandMapper;
import com.example.tinymall.service.TinymallBrandService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TinymallBrandServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-11 10:36
 */
@Service
public class TinymallBrandServiceImpl implements TinymallBrandService {
    @Resource
    private TinymallBrandMapper brandMapper;

    @Override
    public List<TinymallBrand> query(Integer page, Integer limit) {
        return query(page, limit, null, null);
    }

    @Override
    public List<TinymallBrand> query(Integer page, Integer limit, String sort, String order) {
        /*TinymallBrandExample example = new TinymallBrandExample();
        example.or().andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(page, limit);
        return brandMapper.selectByExampleSelective(example, columns);*/
        return Lists.newArrayList();
    }

    @Override
    public TinymallBrand findById(Integer brandId) {
        return brandMapper.selectByPrimaryKey(brandId);
    }
}

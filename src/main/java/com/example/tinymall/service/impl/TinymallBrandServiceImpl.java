package com.example.tinymall.service.impl;

import com.example.tinymall.dao.TinymallBrandMapper;
import com.example.tinymall.domain.TinymallBrand;
import com.example.tinymall.domain.TinymallBrandExample;
import com.example.tinymall.service.TinymallBrandService;
import com.github.pagehelper.PageHelper;
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
    private TinymallBrand.Column[] columns = new TinymallBrand.Column[]{TinymallBrand.Column.id, TinymallBrand.Column.name,
            TinymallBrand.Column.desc, TinymallBrand.Column.picUrl, TinymallBrand.Column.floorPrice};

    @Override
    public List<TinymallBrand> query(Integer page, Integer limit) {
        return query(page, limit, null, null);
    }

    @Override
    public List<TinymallBrand> query(Integer page, Integer limit, String sort, String order) {
        TinymallBrandExample example = new TinymallBrandExample();
        example.or().andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(page, limit);
        return brandMapper.selectByExampleSelective(example, columns);
    }

    @Override
    public TinymallBrand findById(Integer brandId) {
        return brandMapper.selectByPrimaryKey(brandId);
    }
}

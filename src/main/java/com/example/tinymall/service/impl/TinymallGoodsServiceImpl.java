package com.example.tinymall.service.impl;

import com.example.tinymall.dao.TinymallGoodsMapper;
import com.example.tinymall.domain.TinymallGoods;
import com.example.tinymall.domain.TinymallGoodsExample;
import com.example.tinymall.service.TinymallGoodsService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TinymallGoodsServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-10 17:37
 */
@Service
public class TinymallGoodsServiceImpl implements TinymallGoodsService {

    TinymallGoods.Column[] columns = new TinymallGoods.Column[]{TinymallGoods.Column.id, TinymallGoods.Column.name, TinymallGoods.Column.brief,
            TinymallGoods.Column.picUrl, TinymallGoods.Column.isHot, TinymallGoods.Column.isNew, TinymallGoods.Column.counterPrice, TinymallGoods.Column.retailPrice};
    @Resource
    private TinymallGoodsMapper goodsMapper;

    @Override
    public List<TinymallGoods> queryByNew(int offset, int limit) {
        TinymallGoodsExample example = new TinymallGoodsExample();
        example.or().andIsNewEqualTo(true).andIsOnSaleEqualTo(true).andDeletedEqualTo(false);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);

        return goodsMapper.selectByExampleSelective(example, columns);
    }

    @Override
    public List<TinymallGoods> queryByHot(int offset, int limit) {
        TinymallGoodsExample example = new TinymallGoodsExample();
        example.or().andIsHotEqualTo(true).andIsOnSaleEqualTo(true).andDeletedEqualTo(false);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);

        return goodsMapper.selectByExampleSelective(example, columns);
    }

    @Override
    public List<TinymallGoods> queryByCategory(List<Integer> catList, int offset, Integer limit) {
        TinymallGoodsExample example = new TinymallGoodsExample();
        example.or().andCategoryIdIn(catList).andIsOnSaleEqualTo(true).andDeletedEqualTo(false);
        example.setOrderByClause("add_time  desc");
        PageHelper.startPage(offset, limit);

        return goodsMapper.selectByExampleSelective(example, columns);
    }

    @Override
    public List<TinymallGoods> queryByCategory(Integer catId, int offset, int limit) {
        TinymallGoodsExample example = new TinymallGoodsExample();
        example.or().andCategoryIdEqualTo(catId).andIsOnSaleEqualTo(true).andDeletedEqualTo(false);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);

        return goodsMapper.selectByExampleSelective(example, columns);
    }

    @Override
    public Integer queryOnSale() {
        TinymallGoodsExample example = new TinymallGoodsExample();
        example.or().andIsOnSaleEqualTo(true).andDeletedEqualTo(false);
        return (int) goodsMapper.countByExample(example);
    }

    @Override
    public List<TinymallGoods> querySelective(Integer categoryId, Integer brandId, String keywords, Boolean isHot, Boolean isNew, Integer page, Integer limit, String sort, String order) {
        TinymallGoodsExample example = new TinymallGoodsExample();
        TinymallGoodsExample.Criteria criteria1 = example.or();
        TinymallGoodsExample.Criteria criteria2 = example.or();

        if (categoryId != null && categoryId != 0) {
            criteria1.andCategoryIdEqualTo(categoryId);
            criteria2.andCategoryIdEqualTo(categoryId);
        }
        if (brandId != null) {
            criteria1.andBrandIdEqualTo(brandId);
            criteria2.andBrandIdEqualTo(brandId);
        }
        if (isNew != null) {
            criteria1.andIsNewEqualTo(isNew);
            criteria2.andIsNewEqualTo(isNew);
        }
        if (isHot != null) {
            criteria1.andIsHotEqualTo(isHot);
            criteria2.andIsHotEqualTo(isHot);
        }
        if (!StringUtils.isEmpty(keywords)) {
            criteria1.andKeywordsLike("%" + keywords + "%");
            criteria2.andNameLike("%" + keywords + "%");
        }
        criteria1.andIsOnSaleEqualTo(true);
        criteria2.andIsOnSaleEqualTo(true);
        criteria1.andDeletedEqualTo(false);
        criteria2.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);

        return goodsMapper.selectByExampleSelective(example, columns);
    }

    @Override
    public List<Integer> getCatIds(Integer brandId, String keywords, Boolean isHot, Boolean isNew) {
        TinymallGoodsExample example = new TinymallGoodsExample();
        TinymallGoodsExample.Criteria criteria1 = example.or();
        TinymallGoodsExample.Criteria criteria2 = example.or();

        if (brandId != null) {
            criteria1.andBrandIdEqualTo(brandId);
            criteria2.andBrandIdEqualTo(brandId);
        }
        if (isNew != null) {
            criteria1.andIsNewEqualTo(isNew);
            criteria2.andIsNewEqualTo(isNew);
        }
        if (isHot != null) {
            criteria1.andIsHotEqualTo(isHot);
            criteria2.andIsHotEqualTo(isHot);
        }
        if (!StringUtils.isEmpty(keywords)) {
            criteria1.andKeywordsLike("%" + keywords + "%");
            criteria2.andNameLike("%" + keywords + "%");
        }
        criteria1.andIsOnSaleEqualTo(true);
        criteria2.andIsOnSaleEqualTo(true);
        criteria1.andDeletedEqualTo(false);
        criteria2.andDeletedEqualTo(false);

        List<TinymallGoods> goodsList = goodsMapper.selectByExampleSelective(example, TinymallGoods.Column.categoryId);
        List<Integer> cats = new ArrayList<Integer>();
        for (TinymallGoods goods : goodsList) {
            cats.add(goods.getCategoryId());
        }
        return cats;
    }

    @Override
    public TinymallGoods findById(Integer id) {
        TinymallGoodsExample example = new TinymallGoodsExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return goodsMapper.selectOneByExampleWithBLOBs(example);
    }
}

package com.example.tinymall.service.impl;

import com.example.tinymall.entity.TinymallCart;
import com.example.tinymall.mapper.TinymallCartMapper;
import com.example.tinymall.service.TinymallCartService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName TinymallCartServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-15 14:24
 */
@Service
public class TinymallCartServiceImpl implements TinymallCartService {
    @Resource
    private TinymallCartMapper cartMapper;

    @Override
    public TinymallCart queryExist(Integer goodsId, Integer productId, Integer userId) {
        /*TinymallCartExample example = new TinymallCartExample();
        example.or().andGoodsIdEqualTo(goodsId).andProductIdEqualTo(productId).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return cartMapper.selectOneByExample(example);*/
        return null;
    }

    @Override
    public void add(TinymallCart cart) {
        cart.setAddTime(LocalDateTime.now());
        cart.setUpdateTime(LocalDateTime.now());
        cartMapper.insertSelective(cart);
    }

    @Override
    public int updateById(TinymallCart cart) {
        cart.setUpdateTime(LocalDateTime.now());
        return cartMapper.updateByPrimaryKeySelective(cart);
    }

    @Override
    public List<TinymallCart> queryByUid(int userId) {
        /*TinymallCartExample example = new TinymallCartExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return cartMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public List<TinymallCart> queryByUidAndChecked(Integer userId) {
        /*TinymallCartExample example = new TinymallCartExample();
        example.or().andUserIdEqualTo(userId).andCheckedEqualTo(true).andDeletedEqualTo(false);
        return cartMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public int delete(List<Integer> productIdList, int userId) {
        /*TinymallCartExample example = new TinymallCartExample();
        example.or().andUserIdEqualTo(userId).andProductIdIn(productIdList);
        return cartMapper.logicalDeleteByExample(example);*/
        return 0;
    }

    @Override
    public TinymallCart findById(Integer id) {
        return cartMapper.selectByPrimaryKey(id);
    }

    @Override
    public TinymallCart findById(Integer userId, Integer id) {
        /*TinymallCartExample example = new TinymallCartExample();
        example.or().andUserIdEqualTo(userId).andIdEqualTo(id).andDeletedEqualTo(false);
        return cartMapper.selectOneByExample(example);*/
        return null;
    }

    @Override
    public int updateCheck(Integer userId, List<Integer> idsList, Boolean checked) {
        /*TinymallCartExample example = new TinymallCartExample();
        example.or().andUserIdEqualTo(userId).andProductIdIn(idsList).andDeletedEqualTo(false);
        TinymallCart cart = new TinymallCart();
        cart.setChecked(checked);
        cart.setUpdateTime(LocalDateTime.now());
        return cartMapper.updateByExampleSelective(cart, example);*/
        return 0;
    }

    @Override
    public void clearGoods(Integer userId) {
        /*TinymallCartExample example = new TinymallCartExample();
        example.or().andUserIdEqualTo(userId).andCheckedEqualTo(true);
        TinymallCart cart = new TinymallCart();
        cart.setDeleted(true);
        cartMapper.updateByExampleSelective(cart, example);*/
    }

    @Override
    public List<TinymallCart> querySelective(Integer userId, Integer goodsId, Integer page, Integer limit, String sort, String order) {
        /*TinymallCartExample example = new TinymallCartExample();
        TinymallCartExample.Criteria criteria = example.createCriteria();

        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (goodsId != null) {
            criteria.andGoodsIdEqualTo(goodsId);
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return cartMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public void deleteById(Integer id) {
        cartMapper.deleteByIds(String.valueOf(id));
    }

    @Override
    public boolean checkExist(Integer goodsId) {
        /*TinymallCartExample example = new TinymallCartExample();
        example.or().andGoodsIdEqualTo(goodsId).andCheckedEqualTo(true).andDeletedEqualTo(false);
        return cartMapper.countByExample(example) != 0;*/
        return false;
    }

    @Override
    public void updateProduct(Integer id, String goodsSn, String goodsName, BigDecimal price, String url) {
        /*TinymallCart cart = new TinymallCart();
        cart.setPrice(price);
        cart.setPicUrl(url);
        cart.setGoodsSn(goodsSn);
        cart.setGoodsName(goodsName);
        TinymallCartExample example = new TinymallCartExample();
        example.or().andProductIdEqualTo(id);
        cartMapper.updateByExampleSelective(cart, example);*/
    }
}

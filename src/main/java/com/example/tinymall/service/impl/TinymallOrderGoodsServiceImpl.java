package com.example.tinymall.service.impl;

import com.example.tinymall.dao.TinymallOrderGoodsMapper;
import com.example.tinymall.domain.TinymallOrderGoods;
import com.example.tinymall.domain.TinymallOrderGoodsExample;
import com.example.tinymall.service.TinymallOrderGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName TinymallOrderGoodsServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-23 11:32
 */
@Service
public class TinymallOrderGoodsServiceImpl implements TinymallOrderGoodsService {

    @Resource
    private TinymallOrderGoodsMapper orderGoodsMapper;

    @Override
    public int add(TinymallOrderGoods orderGoods) {
        orderGoods.setAddTime(LocalDateTime.now());
        orderGoods.setUpdateTime(LocalDateTime.now());
        return orderGoodsMapper.insertSelective(orderGoods);
    }

    @Override
    public List<TinymallOrderGoods> queryByOid(Integer orderId) {
        TinymallOrderGoodsExample example = new TinymallOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        return orderGoodsMapper.selectByExample(example);
    }

    @Override
    public List<TinymallOrderGoods> findByOidAndGid(Integer orderId, Integer goodsId) {
        TinymallOrderGoodsExample example = new TinymallOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return orderGoodsMapper.selectByExample(example);
    }

    @Override
    public TinymallOrderGoods findById(Integer id) {
        return orderGoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateById(TinymallOrderGoods orderGoods) {
        orderGoods.setUpdateTime(LocalDateTime.now());
        orderGoodsMapper.updateByPrimaryKeySelective(orderGoods);
    }

    @Override
    public Short getComments(Integer orderId) {
        TinymallOrderGoodsExample example = new TinymallOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        long count = orderGoodsMapper.countByExample(example);
        return (short) count;
    }

    @Override
    public boolean checkExist(Integer goodsId) {
        TinymallOrderGoodsExample example = new TinymallOrderGoodsExample();
        example.or().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return orderGoodsMapper.countByExample(example) != 0;
    }

    @Override
    public void deleteByOrderId(Integer orderId) {
        TinymallOrderGoodsExample example = new TinymallOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        orderGoodsMapper.logicalDeleteByExample(example);
    }
}
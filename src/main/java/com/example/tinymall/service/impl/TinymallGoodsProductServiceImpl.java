package com.example.tinymall.service.impl;

import com.example.tinymall.entity.TinymallGoodsProduct;
import com.example.tinymall.mapper.GoodsProductMapper;
import com.example.tinymall.mapper.TinymallGoodsProductMapper;
import com.example.tinymall.service.TinymallGoodsProductService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName TinymallGoodsProductServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-15 11:14
 */
@Service
public class TinymallGoodsProductServiceImpl implements TinymallGoodsProductService {

    @Resource
    private TinymallGoodsProductMapper litemallGoodsProductMapper;
    @Resource
    private GoodsProductMapper goodsProductMapper;

    @Override
    public List<TinymallGoodsProduct> queryByGid(Integer gid) {
        /*TinymallGoodsProductExample example = new TinymallGoodsProductExample();
        example.or().andGoodsIdEqualTo(gid).andDeletedEqualTo(false);
        return litemallGoodsProductMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public TinymallGoodsProduct findById(Integer id) {
        return litemallGoodsProductMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Integer id) {
        litemallGoodsProductMapper.deleteByIds(String.valueOf(id));
    }

    @Override
    public void add(TinymallGoodsProduct goodsProduct) {
        goodsProduct.setAddTime(LocalDateTime.now());
        goodsProduct.setUpdateTime(LocalDateTime.now());
        litemallGoodsProductMapper.insertSelective(goodsProduct);
    }

    @Override
    public int count() {
        /*TinymallGoodsProductExample example = new TinymallGoodsProductExample();
        example.or().andDeletedEqualTo(false);
        return (int) litemallGoodsProductMapper.countByExample(example);*/
        return 0;
    }

    @Override
    public void deleteByGid(Integer gid) {
        /*TinymallGoodsProductExample example = new TinymallGoodsProductExample();
        example.or().andGoodsIdEqualTo(gid);
        litemallGoodsProductMapper.logicalDeleteByExample(example);*/
    }

    @Override
    public int addStock(Integer id, Short num) {
        return goodsProductMapper.addStock(id, num);
    }

    @Override
    public int reduceStock(Integer id, Short num) {
        return goodsProductMapper.reduceStock(id, num);
    }

    @Override
    public void updateById(TinymallGoodsProduct product) {
        product.setUpdateTime(LocalDateTime.now());
        litemallGoodsProductMapper.updateByPrimaryKeySelective(product);
    }
}

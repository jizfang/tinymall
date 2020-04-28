package com.example.tinymall.service.impl;

import com.example.tinymall.dao.GoodsProductMapper;
import com.example.tinymall.dao.TinymallGoodsProductMapper;
import com.example.tinymall.domain.TinymallGoodsProduct;
import com.example.tinymall.domain.TinymallGoodsProductExample;
import com.example.tinymall.service.TinymallGoodsProductService;
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
        TinymallGoodsProductExample example = new TinymallGoodsProductExample();
        example.or().andGoodsIdEqualTo(gid).andDeletedEqualTo(false);
        return litemallGoodsProductMapper.selectByExample(example);
    }

    @Override
    public TinymallGoodsProduct findById(Integer id) {
        return litemallGoodsProductMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Integer id) {
        litemallGoodsProductMapper.logicalDeleteByPrimaryKey(id);
    }

    @Override
    public void add(TinymallGoodsProduct goodsProduct) {
        goodsProduct.setAddTime(LocalDateTime.now());
        goodsProduct.setUpdateTime(LocalDateTime.now());
        litemallGoodsProductMapper.insertSelective(goodsProduct);
    }

    @Override
    public int count() {
        TinymallGoodsProductExample example = new TinymallGoodsProductExample();
        example.or().andDeletedEqualTo(false);
        return (int) litemallGoodsProductMapper.countByExample(example);
    }

    @Override
    public void deleteByGid(Integer gid) {
        TinymallGoodsProductExample example = new TinymallGoodsProductExample();
        example.or().andGoodsIdEqualTo(gid);
        litemallGoodsProductMapper.logicalDeleteByExample(example);
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

package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
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
public class TinymallGoodsProductServiceImpl extends BaseMySqlServiceImpl<TinymallGoodsProduct,Integer> implements TinymallGoodsProductService {

    @Resource
    private TinymallGoodsProductMapper litemallGoodsProductMapper;
    @Resource
    private GoodsProductMapper goodsProductMapper;

    @Override
    public List<TinymallGoodsProduct> queryByGid(Integer gid) {
        TinymallGoodsProduct tinymallGoodsProduct = new TinymallGoodsProduct();
        tinymallGoodsProduct.setGoodsId(gid);
        tinymallGoodsProduct.setDeleted(0);
        return litemallGoodsProductMapper.select(tinymallGoodsProduct);
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
}

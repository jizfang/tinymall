package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.entity.TinymallGoodsAttribute;
import com.example.tinymall.mapper.TinymallGoodsAttributeMapper;
import com.example.tinymall.service.TinymallGoodsAttributeService;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TinymallGoodsAttributeServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-14 21:01
 */
@Service
@Primary
public class TinymallGoodsAttributeServiceImpl extends BaseMySqlServiceImpl<TinymallGoodsAttribute,Integer>
        implements TinymallGoodsAttributeService {
    @Resource
    private TinymallGoodsAttributeMapper goodsAttributeMapper;

    @Override
    public List queryByGid(Integer goodsId) {
        TinymallGoodsAttribute goodsAttribute = new TinymallGoodsAttribute();
        goodsAttribute.setGoodsId(goodsId);
        goodsAttribute.setDeleted(0);
        return goodsAttributeMapper.select(goodsAttribute);
    }
}

package com.example.tinymall.service.impl;

import com.example.tinymall.dao.TinymallGoodsAttributeMapper;
import com.example.tinymall.domain.TinymallGoodsAttributeExample;
import com.example.tinymall.service.TinymallGoodsAttributeService;
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
public class TinymallGoodsAttributeServiceImpl implements TinymallGoodsAttributeService {
    @Resource
    private TinymallGoodsAttributeMapper goodsAttributeMapper;

    @Override
    public List queryByGid(Integer goodsId) {
        TinymallGoodsAttributeExample example = new TinymallGoodsAttributeExample();
        example.or().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return goodsAttributeMapper.selectByExample(example);
    }
}

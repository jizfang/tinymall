package com.example.tinymall.service.impl;

import com.example.tinymall.entity.TinymallGoodsSpecification;
import com.example.tinymall.mapper.TinymallGoodsSpecificationMapper;
import com.example.tinymall.model.vo.SpecificationVO;
import com.example.tinymall.service.TinymallGoodsSpecificationService;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TinymallGoodsSpecificationServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-14 21:08
 */
@Service
@Primary
public class TinymallGoodsSpecificationServiceImpl implements TinymallGoodsSpecificationService {
    @Resource
    private TinymallGoodsSpecificationMapper goodsSpecificationMapper;

    @Override
    public List<TinymallGoodsSpecification> queryByGid(Integer goodsId) {
        TinymallGoodsSpecification specification = new TinymallGoodsSpecification();
        specification.setGoodsId(goodsId);
        specification.setDeleted(0);
        return goodsSpecificationMapper.select(specification);
    }

    @Override
    public Object getSpecificationVoList(Integer id) {
        List<TinymallGoodsSpecification> goodsSpecificationList = queryByGid(id);

        Map<String, SpecificationVO> map = new HashMap<>();
        List<SpecificationVO> specificationVoList = new ArrayList<>();

        for (TinymallGoodsSpecification goodsSpecification : goodsSpecificationList) {
            String specification = goodsSpecification.getSpecification();
            SpecificationVO goodsSpecificationVo = map.get(specification);
            if (goodsSpecificationVo == null) {
                goodsSpecificationVo = new SpecificationVO();
                goodsSpecificationVo.setName(specification);
                List<TinymallGoodsSpecification> valueList = new ArrayList<>();
                valueList.add(goodsSpecification);
                goodsSpecificationVo.setValueList(valueList);
                map.put(specification, goodsSpecificationVo);
                specificationVoList.add(goodsSpecificationVo);
            } else {
                List<TinymallGoodsSpecification> valueList = goodsSpecificationVo.getValueList();
                valueList.add(goodsSpecification);
            }
        }

        return specificationVoList;
    }
}

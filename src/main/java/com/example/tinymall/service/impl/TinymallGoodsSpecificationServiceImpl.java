package com.example.tinymall.service.impl;

import com.example.tinymall.dao.TinymallGoodsSpecificationMapper;
import com.example.tinymall.domain.TinymallGoodsSpecification;
import com.example.tinymall.domain.TinymallGoodsSpecificationExample;
import com.example.tinymall.domain.vo.SpecificationVO;
import com.example.tinymall.service.TinymallGoodsSpecificationService;
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
public class TinymallGoodsSpecificationServiceImpl implements TinymallGoodsSpecificationService {
    @Resource
    private TinymallGoodsSpecificationMapper goodsSpecificationMapper;

    @Override
    public List<TinymallGoodsSpecification> queryByGid(Integer id) {
        TinymallGoodsSpecificationExample example = new TinymallGoodsSpecificationExample();
        example.or().andGoodsIdEqualTo(id).andDeletedEqualTo(false);
        return goodsSpecificationMapper.selectByExample(example);
    }

    @Override
    public TinymallGoodsSpecification findById(Integer id) {
        return goodsSpecificationMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteByGid(Integer gid) {
        TinymallGoodsSpecificationExample example = new TinymallGoodsSpecificationExample();
        example.or().andGoodsIdEqualTo(gid);
        goodsSpecificationMapper.logicalDeleteByExample(example);
    }

    @Override
    public void add(TinymallGoodsSpecification goodsSpecification) {
        goodsSpecification.setAddTime(LocalDateTime.now());
        goodsSpecification.setUpdateTime(LocalDateTime.now());
        goodsSpecificationMapper.insertSelective(goodsSpecification);
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

    @Override
    public void updateById(TinymallGoodsSpecification specification) {
        specification.setUpdateTime(LocalDateTime.now());
        goodsSpecificationMapper.updateByPrimaryKeySelective(specification);
    }
}

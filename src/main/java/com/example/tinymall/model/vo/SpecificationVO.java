package com.example.tinymall.model.vo;

import com.example.tinymall.entity.TinymallGoodsSpecification;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName SpecificationVO
 * @Description
 * @Author jzf
 * @Date 2020-4-14 21:06
 */
@Getter
@Setter
public class SpecificationVO {
    private String name;
    private List<TinymallGoodsSpecification> valueList;
}

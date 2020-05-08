package com.example.tinymall.model.dto;

import com.example.tinymall.entity.TinymallGoods;
import com.example.tinymall.entity.TinymallGoodsAttribute;
import com.example.tinymall.entity.TinymallGoodsProduct;
import com.example.tinymall.entity.TinymallGoodsSpecification;
import lombok.Data;

/**
 * @ClassName GoodsDTO
 * @Description
 * @Author jzf
 * @Date 2020-4-30 15:43
 */
@Data
public class GoodsDTO {
    TinymallGoods goods;
    TinymallGoodsSpecification[] specifications;
    TinymallGoodsAttribute[] attributes;
    TinymallGoodsProduct[] products;
}

package com.example.tinymall.domain.dto;

import com.example.tinymall.domain.TinymallGoods;
import com.example.tinymall.domain.TinymallGoodsAttribute;
import com.example.tinymall.domain.TinymallGoodsProduct;
import com.example.tinymall.domain.TinymallGoodsSpecification;
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

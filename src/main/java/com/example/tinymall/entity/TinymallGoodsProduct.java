package com.example.tinymall.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;

import com.example.tinymall.model.po.BasePO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_goods_product")
public class TinymallGoodsProduct extends BasePO<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品表的商品ID
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 商品规格值列表，采用JSON数组格式
     */
    //@Column(name = "specifications")
    //private String[] specifications;

    /**
     * 商品货品价格
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 商品货品数量
     */
    @Column(name = "number")
    private Integer number;

    /**
     * 商品货品图片
     */
    @Column(name = "url")
    private String url;
}
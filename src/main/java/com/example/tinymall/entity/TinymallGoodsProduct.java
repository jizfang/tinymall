package com.example.tinymall.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_goods_product")
public class TinymallGoodsProduct {
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

    /**
     * 创建时间
     */
    @Column(name = "add_time")
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    @Column(name = "deleted")
    private Boolean deleted;
}
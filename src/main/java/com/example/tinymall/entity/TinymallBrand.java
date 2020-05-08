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
@Table(name = "tinymall_brand")
public class TinymallBrand {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 品牌商名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 品牌商简介
     */
    @Column(name = "desc")
    private String desc;

    /**
     * 品牌商页的品牌商图片
     */
    @Column(name = "pic_url")
    private String picUrl;

    @Column(name = "sort_order")
    private Byte sortOrder;

    /**
     * 品牌商的商品低价，仅用于页面展示
     */
    @Column(name = "floor_price")
    private BigDecimal floorPrice;

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
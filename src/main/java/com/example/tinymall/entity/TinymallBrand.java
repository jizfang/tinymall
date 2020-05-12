package com.example.tinymall.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.example.tinymall.model.po.BasePO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_brand")
public class TinymallBrand extends BasePO<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 品牌商名称
     */
    @Column(name = "`name`")
    @NotEmpty(message = "品牌商名称不能为空")
    private String name;

    /**
     * 品牌商简介
     */
    @Column(name = "`desc`")
    @NotEmpty(message = "品牌商简介不能为空")
    private String desc;

    /**
     * 品牌商页的品牌商图片
     */
    @Column(name = "pic_url")
    @NotEmpty(message = "品牌商页的品牌商图片不能为空")
    private String picUrl;

    @Column(name = "sort_order")
    private Byte sortOrder;

    /**
     * 品牌商的商品低价，仅用于页面展示
     */
    @Column(name = "floor_price")
    private BigDecimal floorPrice;
}
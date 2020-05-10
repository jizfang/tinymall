package com.example.tinymall.entity;

import java.math.BigDecimal;
import javax.persistence.*;

import com.example.tinymall.model.po.BasePO;
import com.example.tinymall.mybatis.JsonStringArrayTypeHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.ColumnType;

@Getter
@Setter
@ToString
@Table(name = "tinymall_goods")
public class TinymallGoods extends BasePO<Integer> {
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 商品编号
     */
    @Column(name = "goods_sn")
    private String goodsSn;

    /**
     * 商品名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 商品所属类目ID
     */
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "brand_id")
    private Integer brandId;

    /**
     * 商品宣传图片列表，采用JSON数组格式
     */
    @Column(name = "gallery")
    @ColumnType(typeHandler = JsonStringArrayTypeHandler.class)
    private String[] gallery;

    /**
     * 商品关键字，采用逗号间隔
     */
    @Column(name = "keywords")
    private String keywords;

    /**
     * 商品简介
     */
    @Column(name = "brief")
    private String brief;

    /**
     * 是否上架
     */
    @Column(name = "is_on_sale")
    private Boolean isOnSale;

    @Column(name = "sort_order")
    private Short sortOrder;

    /**
     * 商品页面商品图片
     */
    @Column(name = "pic_url")
    private String picUrl;

    /**
     * 商品分享朋友圈图片
     */
    @Column(name = "share_url")
    private String shareUrl;

    /**
     * 是否新品首发，如果设置则可以在新品首发页面展示
     */
    @Column(name = "is_new")
    private Boolean isNew;

    /**
     * 是否人气推荐，如果设置则可以在人气推荐页面展示
     */
    @Column(name = "is_hot")
    private Boolean isHot;

    /**
     * 商品单位，例如件、盒
     */
    @Column(name = "unit")
    private String unit;

    /**
     * 专柜价格
     */
    @Column(name = "counter_price")
    private BigDecimal counterPrice;

    /**
     * 零售价格
     */
    @Column(name = "retail_price")
    private BigDecimal retailPrice;

    /**
     * 商品详细介绍，是富文本格式
     */
    @Column(name = "detail")
    private String detail;
}
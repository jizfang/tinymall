package com.example.tinymall.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@Table(name = "tinymall_cart")
public class TinymallCart extends BasePO<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户表的用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 商品表的商品ID
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 商品编号
     */
    @Column(name = "goods_sn")
    private String goodsSn;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品货品表的货品ID
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 商品货品的价格
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 商品货品的数量
     */
    @Column(name = "number")
    private Short number;

    /**
     * 商品规格值列表，采用JSON数组格式
     */
    @Column(name = "specifications")
    //@ColumnType(typeHandler = JsonStringArrayTypeHandler.class)
    private String[] specifications;

    /**
     * 购物车中商品是否选择状态
     */
    @Column(name = "checked")
    private Boolean checked;

    /**
     * 商品图片或者商品货品图片
     */
    @Column(name = "pic_url")
    private String picUrl;
}
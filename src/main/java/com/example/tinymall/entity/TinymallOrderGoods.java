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
@Table(name = "tinymall_order_goods")
public class TinymallOrderGoods extends BasePO<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单表的订单ID
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 商品表的商品ID
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品编号
     */
    @Column(name = "goods_sn")
    private String goodsSn;

    /**
     * 商品货品表的货品ID
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 商品货品的购买数量
     */
    @Column(name = "number")
    private Short number;

    /**
     * 商品货品的售价
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 商品货品的规格列表
     */
    @Column(name = "specifications")
    private String[] specifications;

    /**
     * 商品货品图片或者商品图片
     */
    @Column(name = "pic_url")
    private String picUrl;

    /**
     * 订单商品评论，如果是-1，则超期不能评价；如果是0，则可以评价；如果其他值，则是comment表里面的评论ID。
     */
    @Column(name = "comment")
    private Integer comment;
}
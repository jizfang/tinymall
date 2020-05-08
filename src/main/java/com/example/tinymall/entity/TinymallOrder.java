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
@Table(name = "tinymall_order")
public class TinymallOrder {
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
     * 订单编号
     */
    @Column(name = "order_sn")
    private String orderSn;

    /**
     * 订单状态
     */
    @Column(name = "order_status")
    private Short orderStatus;

    /**
     * 售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消
     */
    @Column(name = "aftersale_status")
    private Short aftersaleStatus;

    /**
     * 收货人名称
     */
    @Column(name = "consignee")
    private String consignee;

    /**
     * 收货人手机号
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 收货具体地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 用户订单留言
     */
    @Column(name = "message")
    private String message;

    /**
     * 商品总费用
     */
    @Column(name = "goods_price")
    private BigDecimal goodsPrice;

    /**
     * 配送费用
     */
    @Column(name = "freight_price")
    private BigDecimal freightPrice;

    /**
     * 优惠券减免
     */
    @Column(name = "coupon_price")
    private BigDecimal couponPrice;

    /**
     * 用户积分减免
     */
    @Column(name = "integral_price")
    private BigDecimal integralPrice;

    /**
     * 团购优惠价减免
     */
    @Column(name = "groupon_price")
    private BigDecimal grouponPrice;

    /**
     * 订单费用， = goods_price + freight_price - coupon_price
     */
    @Column(name = "order_price")
    private BigDecimal orderPrice;

    /**
     * 实付费用， = order_price - integral_price
     */
    @Column(name = "actual_price")
    private BigDecimal actualPrice;

    /**
     * 微信付款编号
     */
    @Column(name = "pay_id")
    private String payId;

    /**
     * 微信付款时间
     */
    @Column(name = "pay_time")
    private LocalDateTime payTime;

    /**
     * 发货编号
     */
    @Column(name = "ship_sn")
    private String shipSn;

    /**
     * 发货快递公司
     */
    @Column(name = "ship_channel")
    private String shipChannel;

    /**
     * 发货开始时间
     */
    @Column(name = "ship_time")
    private LocalDateTime shipTime;

    /**
     * 实际退款金额，（有可能退款金额小于实际支付金额）
     */
    @Column(name = "refund_amount")
    private BigDecimal refundAmount;

    /**
     * 退款方式
     */
    @Column(name = "refund_type")
    private String refundType;

    /**
     * 退款备注
     */
    @Column(name = "refund_content")
    private String refundContent;

    /**
     * 退款时间
     */
    @Column(name = "refund_time")
    private LocalDateTime refundTime;

    /**
     * 用户确认收货时间
     */
    @Column(name = "confirm_time")
    private LocalDateTime confirmTime;

    /**
     * 待评价订单商品数量
     */
    @Column(name = "comments")
    private Short comments;

    /**
     * 订单关闭时间
     */
    @Column(name = "end_time")
    private LocalDateTime endTime;

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
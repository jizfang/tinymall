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
@Table(name = "tinymall_coupon")
public class TinymallCoupon extends BasePO<Integer> {
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 优惠券名称
     */
    @Column(name = "`name`")
    @NotEmpty(message = "优惠券名称不能为空")
    private String name;

    /**
     * 优惠券介绍，通常是显示优惠券使用限制文字
     */
    @Column(name = "`desc`")
    private String desc;

    /**
     * 优惠券标签，例如新人专用
     */
    @Column(name = "tag")
    private String tag;

    /**
     * 优惠券数量，如果是0，则是无限量
     */
    @Column(name = "total")
    private Integer total;

    /**
     * 优惠金额，
     */
    @Column(name = "discount")
    private BigDecimal discount;

    /**
     * 最少消费金额才能使用优惠券。
     */
    @Column(name = "min")
    private BigDecimal min;

    /**
     * 用户领券限制数量，如果是0，则是不限制；默认是1，限领一张.
     */
    @Column(name = "`limit`")
    private Short limit;

    /**
     * 优惠券赠送类型，如果是0则通用券，用户领取；如果是1，则是注册赠券；如果是2，则是优惠券码兑换；
     */
    @Column(name = "type")
    private Short type;

    /**
     * 优惠券状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架。
     */
    @Column(name = "`status`")
    private Short status;

    /**
     * 商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。
     */
    @Column(name = "goods_type")
    private Short goodsType;

    /**
     * 商品限制值，goods_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合。
     */
    //@Column(name = "goods_value")
    //private Integer[] goodsValue;

    /**
     * 优惠券兑换码
     */
    @Column(name = "`code`")
    private String code;

    /**
     * 有效时间限制，如果是0，则基于领取时间的有效天数days；如果是1，则start_time和end_time是优惠券有效期；
     */
    @Column(name = "time_type")
    private Short timeType;

    /**
     * 基于领取时间的有效天数days。
     */
    @Column(name = "days")
    private Short days;

    /**
     * 使用券开始时间
     */
    @Column(name = "start_time")
    private LocalDateTime startTime;

    /**
     * 使用券截至时间
     */
    @Column(name = "end_time")
    private LocalDateTime endTime;
}
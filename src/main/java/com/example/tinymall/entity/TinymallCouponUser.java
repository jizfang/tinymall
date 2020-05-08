package com.example.tinymall.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_coupon_user")
public class TinymallCouponUser {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 优惠券ID
     */
    @Column(name = "coupon_id")
    private Integer couponId;

    /**
     * 使用状态, 如果是0则未使用；如果是1则已使用；如果是2则已过期；如果是3则已经下架；
     */
    @Column(name = "status")
    private Short status;

    /**
     * 使用时间
     */
    @Column(name = "used_time")
    private LocalDateTime usedTime;

    /**
     * 有效期开始时间
     */
    @Column(name = "start_time")
    private LocalDateTime startTime;

    /**
     * 有效期截至时间
     */
    @Column(name = "end_time")
    private LocalDateTime endTime;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Integer orderId;

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
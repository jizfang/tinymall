package com.example.tinymall.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_groupon")
public class TinymallGroupon {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 关联的订单ID
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 如果是开团用户，则groupon_id是0；如果是参团用户，则groupon_id是团购活动ID
     */
    @Column(name = "groupon_id")
    private Integer grouponId;

    /**
     * 团购规则ID，关联tinymall_groupon_rules表ID字段
     */
    @Column(name = "rules_id")
    private Integer rulesId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 团购分享图片地址
     */
    @Column(name = "share_url")
    private String shareUrl;

    /**
     * 开团用户ID
     */
    @Column(name = "creator_user_id")
    private Integer creatorUserId;

    /**
     * 开团时间
     */
    @Column(name = "creator_user_time")
    private LocalDateTime creatorUserTime;

    /**
     * 团购活动状态，开团未支付则0，开团中则1，开团失败则2
     */
    @Column(name = "status")
    private Short status;

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
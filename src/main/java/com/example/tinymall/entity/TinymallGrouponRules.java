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
@Table(name = "tinymall_groupon_rules")
public class TinymallGrouponRules extends BasePO<Integer> {
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
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品图片或者商品货品图片
     */
    @Column(name = "pic_url")
    private String picUrl;

    /**
     * 优惠金额
     */
    @Column(name = "discount")
    private BigDecimal discount;

    /**
     * 达到优惠条件的人数
     */
    @Column(name = "discount_member")
    private Integer discountMember;

    /**
     * 团购过期时间
     */
    @Column(name = "expire_time")
    private LocalDateTime expireTime;

    /**
     * 团购规则状态，正常上线则0，到期自动下线则1，管理手动下线则2
     */
    @Column(name = "status")
    private Short status;
}
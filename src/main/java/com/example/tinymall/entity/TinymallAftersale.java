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
@Table(name = "tinymall_aftersale")
public class TinymallAftersale extends BasePO<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 售后编号
     */
    @Column(name = "aftersale_sn")
    private String aftersaleSn;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 售后类型，0是未收货退款，1是已收货（无需退货）退款，2用户退货退款
     */
    @Column(name = "type")
    private Short type;

    /**
     * 退款原因
     */
    @Column(name = "reason")
    private String reason;

    /**
     * 退款金额
     */
    @Column(name = "amount")
    private BigDecimal amount;

    /**
     * 退款凭证图片链接数组
     */
    @Column(name = "pictures")
    @ColumnType(typeHandler = JsonStringArrayTypeHandler.class)
    private String[] pictures;

    /**
     * 退款说明
     */
    @Column(name = "comment")
    private String comment;

    /**
     * 售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消
     */
    @Column(name = "status")
    private Short status;

    /**
     * 管理员操作时间
     */
    @Column(name = "handle_time")
    private LocalDateTime handleTime;
    
}
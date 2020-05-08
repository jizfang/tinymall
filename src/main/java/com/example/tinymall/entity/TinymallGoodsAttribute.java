package com.example.tinymall.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_goods_attribute")
public class TinymallGoodsAttribute {
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
     * 商品参数名称
     */
    @Column(name = "attribute")
    private String attribute;

    /**
     * 商品参数值
     */
    @Column(name = "value")
    private String value;

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
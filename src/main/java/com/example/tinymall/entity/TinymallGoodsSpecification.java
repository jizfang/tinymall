package com.example.tinymall.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

import com.example.tinymall.model.po.BasePO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_goods_specification")
public class TinymallGoodsSpecification extends BasePO<Integer> {
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
     * 商品规格名称
     */
    @Column(name = "specification")
    private String specification;

    /**
     * 商品规格值
     */
    @Column(name = "value")
    private String value;

    /**
     * 商品规格图片
     */
    @Column(name = "pic_url")
    private String picUrl;
}
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
@Table(name = "tinymall_footprint")
public class TinymallFootprint extends BasePO<Integer> {
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 用户表的用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 浏览商品ID
     */
    @Column(name = "goods_id")
    private Integer goodsId;

}
package com.example.tinymall.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_collect")
public class TinymallCollect {
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
     * 如果type=0，则是商品ID；如果type=1，则是专题ID
     */
    @Column(name = "value_id")
    private Integer valueId;

    /**
     * 收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID
     */
    @Column(name = "type")
    private Byte type;

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
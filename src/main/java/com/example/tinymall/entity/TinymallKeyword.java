package com.example.tinymall.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_keyword")
public class TinymallKeyword {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 关键字
     */
    @Column(name = "keyword")
    private String keyword;

    /**
     * 关键字的跳转链接
     */
    @Column(name = "url")
    private String url;

    /**
     * 是否是热门关键字
     */
    @Column(name = "is_hot")
    private Boolean isHot;

    /**
     * 是否是默认关键字
     */
    @Column(name = "is_default")
    private Boolean isDefault;

    /**
     * 排序
     */
    @Column(name = "sort_order")
    private Integer sortOrder;

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
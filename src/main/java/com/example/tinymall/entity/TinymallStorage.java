package com.example.tinymall.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_storage")
public class TinymallStorage {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文件的唯一索引
     */
    @Column(name = "key")
    private String key;

    /**
     * 文件名
     */
    @Column(name = "name")
    private String name;

    /**
     * 文件类型
     */
    @Column(name = "type")
    private String type;

    /**
     * 文件大小
     */
    @Column(name = "size")
    private Integer size;

    /**
     * 文件访问链接
     */
    @Column(name = "url")
    private String url;

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
package com.example.tinymall.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_notice")
public class TinymallNotice {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 通知标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 通知内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 创建通知的管理员ID，如果是系统内置通知则是0.
     */
    @Column(name = "admin_id")
    private Integer adminId;

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
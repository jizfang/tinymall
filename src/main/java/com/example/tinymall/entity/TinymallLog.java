package com.example.tinymall.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_log")
public class TinymallLog {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 管理员
     */
    @Column(name = "admin")
    private String admin;

    /**
     * 管理员地址
     */
    @Column(name = "ip")
    private String ip;

    /**
     * 操作分类
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 操作动作
     */
    @Column(name = "action")
    private String action;

    /**
     * 操作状态
     */
    @Column(name = "status")
    private Boolean status;

    /**
     * 操作结果，或者成功消息，或者失败消息
     */
    @Column(name = "result")
    private String result;

    /**
     * 补充信息
     */
    @Column(name = "comment")
    private String comment;

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
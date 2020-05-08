package com.example.tinymall.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_notice_admin")
public class TinymallNoticeAdmin {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 通知ID
     */
    @Column(name = "notice_id")
    private Integer noticeId;

    /**
     * 通知标题
     */
    @Column(name = "notice_title")
    private String noticeTitle;

    /**
     * 接收通知的管理员ID
     */
    @Column(name = "admin_id")
    private Integer adminId;

    /**
     * 阅读时间，如果是NULL则是未读状态
     */
    @Column(name = "read_time")
    private LocalDateTime readTime;

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
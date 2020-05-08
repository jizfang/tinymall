package com.example.tinymall.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_feedback")
public class TinymallFeedback {
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
     * 用户名称
     */
    @Column(name = "username")
    private String username;

    /**
     * 手机号
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 反馈类型
     */
    @Column(name = "feed_type")
    private String feedType;

    /**
     * 反馈内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 是否含有图片
     */
    @Column(name = "has_picture")
    private Boolean hasPicture;

    /**
     * 图片地址列表，采用JSON数组格式
     */
    //@Column(name = "pic_urls")
    //private String[] picUrls;

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
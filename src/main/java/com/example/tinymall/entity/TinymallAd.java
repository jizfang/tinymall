package com.example.tinymall.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_ad")
public class TinymallAd {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 广告标题
     */
    @Column(name = "name")
    private String name;

    /**
     * 所广告的商品页面或者活动页面链接地址
     */
    @Column(name = "link")
    private String link;

    /**
     * 广告宣传图片
     */
    @Column(name = "url")
    private String url;

    /**
     * 广告位置：1则是首页
     */
    @Column(name = "position")
    private Byte position;

    /**
     * 活动内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 广告开始时间
     */
    @Column(name = "start_time")
    private LocalDateTime startTime;

    /**
     * 广告结束时间
     */
    @Column(name = "end_time")
    private LocalDateTime endTime;

    /**
     * 是否启动
     */
    @Column(name = "enabled")
    private Boolean enabled;

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
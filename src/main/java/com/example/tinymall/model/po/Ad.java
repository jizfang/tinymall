package com.example.tinymall.model.po;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @ClassName Ad
 * @Description 广告po
 * @Author jzf
 * @Date 2020-5-8 18:32
 */
@ApiModel("广告PO")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tinymall_ad")
public class Ad extends BasePO<Integer>{
    @Id
    private Integer id;

    /**
     * 广告标题
     */
    private String name;

    /**
     * 所广告的商品页面或者活动页面链接地址
     */
    private String link;

    /**
     * 广告宣传图片
     */
    private String url;

    /**
     * 广告位置：1则是首页
     */
    private Byte position;

    /**
     * 活动内容
     */
    private String content;

    /**
     * 广告开始时间
     */
    private LocalDateTime startTime;

    /**
     * 广告结束时间
     */
    private LocalDateTime endTime;

    /**
     * 是否启动
     */
    private Boolean enabled;

}

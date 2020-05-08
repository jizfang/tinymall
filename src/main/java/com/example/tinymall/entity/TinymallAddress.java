package com.example.tinymall.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_address")
public class TinymallAddress {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 收货人名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 用户表的用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 行政区域表的省ID
     */
    @Column(name = "province")
    private String province;

    /**
     * 行政区域表的市ID
     */
    @Column(name = "city")
    private String city;

    /**
     * 行政区域表的区县ID
     */
    @Column(name = "county")
    private String county;

    /**
     * 详细收货地址
     */
    @Column(name = "address_detail")
    private String addressDetail;

    /**
     * 地区编码
     */
    @Column(name = "area_code")
    private String areaCode;

    /**
     * 邮政编码
     */
    @Column(name = "postal_code")
    private String postalCode;

    /**
     * 手机号码
     */
    @Column(name = "tel")
    private String tel;

    /**
     * 是否默认地址
     */
    @Column(name = "is_default")
    private Boolean isDefault;

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
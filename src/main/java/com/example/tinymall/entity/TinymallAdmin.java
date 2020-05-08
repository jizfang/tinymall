package com.example.tinymall.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_admin")
public class TinymallAdmin {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 管理员名称
     */
    @Column(name = "username")
    private String username;

    /**
     * 管理员密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 最近一次登录IP地址
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 最近一次登录时间
     */
    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * 头像图片
     */
    @Column(name = "avatar")
    private String avatar;

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

    /**
     * 角色列表
     */
    //@Column(name = "role_ids")
    //private Integer[] roleIds;
}
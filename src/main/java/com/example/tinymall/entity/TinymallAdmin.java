package com.example.tinymall.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

import com.example.tinymall.model.po.BasePO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_admin")
public class TinymallAdmin extends BasePO<Integer> {
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
     * 角色列表
     */
    @Column(name = "role_ids")
    private String roleIds;
}
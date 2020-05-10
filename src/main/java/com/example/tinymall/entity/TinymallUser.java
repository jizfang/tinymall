package com.example.tinymall.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;

import com.example.tinymall.model.po.BasePO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_user")
public class TinymallUser extends BasePO<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名称
     */
    @Column(name = "username")
    private String username;

    /**
     * 用户密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 性别：0 未知， 1男， 1 女
     */
    @Column(name = "gender")
    private Byte gender;

    /**
     * 生日
     */
    @Column(name = "birthday")
    private LocalDate birthday;

    /**
     * 最近一次登录时间
     */
    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * 最近一次登录IP地址
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 0 普通用户，1 VIP用户，2 高级VIP用户
     */
    @Column(name = "user_level")
    private Byte userLevel;

    /**
     * 用户昵称或网络名称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 用户手机号码
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 用户头像图片
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 微信登录openid
     */
    @Column(name = "weixin_openid")
    private String weixinOpenid;

    /**
     * 微信登录会话KEY
     */
    @Column(name = "session_key")
    private String sessionKey;

    /**
     * 0 可用, 1 禁用, 2 注销
     */
    @Column(name = "status")
    private Byte status;
}
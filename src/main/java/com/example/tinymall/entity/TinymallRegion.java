package com.example.tinymall.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_region")
public class TinymallRegion {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 行政区域父ID，例如区县的pid指向市，市的pid指向省，省的pid则是0
     */
    @Column(name = "pid")
    private Integer pid;

    /**
     * 行政区域名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 行政区域类型，如如1则是省， 如果是2则是市，如果是3则是区县
     */
    @Column(name = "type")
    private Byte type;

    /**
     * 行政区域编码
     */
    @Column(name = "code")
    private Integer code;
}
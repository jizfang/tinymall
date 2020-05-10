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
@Table(name = "tinymall_system")
public class TinymallSystem extends BasePO<Integer> {
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 系统配置名
     */
    @Column(name = "key_name")
    private String keyName;

    /**
     * 系统配置值
     */
    @Column(name = "key_value")
    private String keyValue;

}
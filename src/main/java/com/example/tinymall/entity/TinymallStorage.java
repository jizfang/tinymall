package com.example.tinymall.entity;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

import com.example.tinymall.model.po.BasePO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_storage")
public class TinymallStorage extends BasePO<Integer> {
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 文件的唯一索引
     */
    @Column(name = "storage_key")
    private String storageKey;

    /**
     * 文件名
     */
    @Column(name = "storage_name")
    private String storageName;

    /**
     * 文件类型
     */
    @Column(name = "storage_type")
    private String storageType;

    /**
     * 文件大小
     */
    @Column(name = "storage_size")
    private Integer storageSize;

    /**
     * 文件访问链接
     */
    @Column(name = "storage_url")
    private String storageUrl;
}
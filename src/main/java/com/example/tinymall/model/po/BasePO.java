package com.example.tinymall.model.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @ClassName BasePO
 * @Description 基础PO类
 * @Author jzf
 * @Date 2020-5-8 15:29
 */
@Data
public abstract class BasePO<PK> implements PO<PK>{

    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private Long createTime;

    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_time")
    private Long updateTime;

    @ApiModelProperty(value = "逻辑删除")
    @Column(name = "deleted")
    private Boolean deleted;
}

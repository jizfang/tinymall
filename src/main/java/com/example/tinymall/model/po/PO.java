package com.example.tinymall.model.po;

import com.example.tinymall.model.Model;

import java.util.Date;

/**
 * @ClassName BasePO
 * @Description 基础po对象接口
 * @Author jzf
 * @Date 2020-5-8 15:27
 */
public interface PO<PK> extends Model {

    PK getId();

    void setId(PK id);

    Date getCreateTime();

    void setCreateTime(Date createTime);

    Date getUpdateTime();

    void setUpdateTime(Date updateTime);
}

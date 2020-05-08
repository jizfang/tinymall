package com.example.tinymall.common.mineservice;

/**
 * @ClassName InsertService
 * @Description 基础插入服务
 * @Author jzf
 * @Date 2020-5-8 15:39
 */
public interface InsertService<E,PK> {
    /**
     * 添加一条数据
     *
     * @param record 要添加的数据
     * @return 添加后生成的主键
     */
    PK insert(E record);
}

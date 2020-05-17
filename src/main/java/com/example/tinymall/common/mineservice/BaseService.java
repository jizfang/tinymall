package com.example.tinymall.common.mineservice;

import java.util.List;

/**
 * @ClassName BaseService
 * @Description 基础service接口
 * @Author jzf
 * @Date 2020-5-8 15:32
 */
public interface BaseService<E,PK> extends InsertService<E,PK>,
        UpdateService<E,PK>,
        DeleteService<PK>,
        SelectService<E, PK> {

    /**
     * 根据条件查询数量
     * @param record
     * @return
     */
    int selectCount(E record);

    /**
     * 根据条件查询list
     * @param record
     * @return
     */
    List<E> selectByCondition(E record);
}

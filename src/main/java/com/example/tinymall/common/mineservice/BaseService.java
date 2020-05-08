package com.example.tinymall.common.mineservice;

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
}

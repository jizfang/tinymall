package com.example.tinymall.common.minemappers;

import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * @ClassName MyBatchUpdateMapper
 * @Description 批量更新接口
 * @Author jzf
 * @Date 2020-5-8 11:42
 */
public interface MyBatchUpdateMapper<T> {

    @UpdateProvider(type=MyBatchUpdateProvider.class, method="dynamicSQL")
    int batchUpdate(List<T> list);
}

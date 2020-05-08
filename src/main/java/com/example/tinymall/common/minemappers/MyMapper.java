package com.example.tinymall.common.minemappers;

import tk.mybatis.mapper.common.base.select.SelectAllMapper;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;

/**
 * @ClassName MyMapper
 * @Description 自定义通用mapper接口
 * @Author jzf
 * @Date 2020-5-8 11:43
 */
public interface MyMapper<T> extends CommonSelectMapper<T>,
                          CommonInsertMapper<T>,
                          CommonDeleteMapper<T>,
                          UpdateMapper<T>,
                          MyBatchUpdateMapper<T>{
}

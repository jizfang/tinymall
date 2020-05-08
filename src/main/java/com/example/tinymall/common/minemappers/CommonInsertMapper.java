package com.example.tinymall.common.minemappers;

import org.apache.logging.log4j.Marker;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;

/**
 * @ClassName InsertMapper
 * @Description insert功能选择
 * @Author jzf
 * @Date 2020-5-8 15:05
 */
public interface CommonInsertMapper<T> extends Marker,
        InsertMapper<T>,
        InsertSelectiveMapper<T>,
        MySqlMapper<T> {
}

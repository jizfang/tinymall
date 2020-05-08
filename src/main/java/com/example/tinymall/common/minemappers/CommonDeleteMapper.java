package com.example.tinymall.common.minemappers;

import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.base.delete.DeleteByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.delete.DeleteMapper;
import tk.mybatis.mapper.common.condition.DeleteByConditionMapper;
import tk.mybatis.mapper.common.ids.DeleteByIdsMapper;

/**
 * @ClassName CommonDeleteMapper
 * @Description delete功能选择
 * @Author jzf
 * @Date 2020-5-8 15:08
 */
public interface CommonDeleteMapper<T> extends Marker,
        DeleteMapper<T>,
        DeleteByPrimaryKeyMapper<T>,
        DeleteByConditionMapper<T>,
        DeleteByIdsMapper<T> {
}

package com.example.tinymall.mapper;

import com.example.tinymall.common.minemappers.MyMapper;
import com.example.tinymall.entity.TinymallCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TinymallCategoryMapper extends MyMapper<TinymallCategory> {
    /**
     * 查询有效的类目
     * @param pid
     * @return
     */
    List<TinymallCategory> selectValidCategory(@Param("pid") Integer pid);
}
package com.example.tinymall.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @ClassName GoodsProductMapper
 * @Description
 * @Author jzf
 * @Date 2020-4-15 11:16
 */
public interface GoodsProductMapper {
    int addStock(@Param("id") Integer id, @Param("num") Short num);

    int reduceStock(@Param("id") Integer id, @Param("num") Short num);
}

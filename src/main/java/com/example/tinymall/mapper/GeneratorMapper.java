package com.example.tinymall.mapper;

import com.example.tinymall.model.dto.QueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName GeneratorMapper
 * @Description
 * @Author jzf
 * @Date 2020-7-22 16:08
 */
public interface GeneratorMapper {
    List<Map<String, Object>> queryList(@Param("tableName") String tableName);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);
}

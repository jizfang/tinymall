package com.example.tinymall.service;

import com.example.tinymall.model.dto.QueryDTO;
import com.example.tinymall.model.dto.TableDTO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName GeneratorService
 * @Description 代码生成service
 * @Author jzf
 * @Date 2020-7-22 16:04
 */
public interface GeneratorService {
    List<Map<String, Object>> queryList(String tableName);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);

    byte[] generatorCode(List<TableDTO> tableDTOList);
}

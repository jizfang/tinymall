package com.example.tinymall.service.impl;

import com.example.tinymall.core.utils.GenUtils;
import com.example.tinymall.mapper.GeneratorMapper;
import com.example.tinymall.model.dto.QueryDTO;
import com.example.tinymall.model.dto.TableDTO;
import com.example.tinymall.service.GeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;


/**
 * @ClassName GeneratorServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-7-22 16:06
 */
@Service("generatorService")
public class GeneratorServiceImpl implements GeneratorService {
    @Autowired
    private GeneratorMapper generatorMapper;

    @Override
    public List<Map<String, Object>> queryList(String tableName) {
        return generatorMapper.queryList(tableName);
    }

    @Override
    public Map<String, String> queryTable(String tableName) {
        return generatorMapper.queryTable(tableName);
    }

    @Override
    public List<Map<String, String>> queryColumns(String tableName) {
        return generatorMapper.queryColumns(tableName);
    }

    @Override
    public byte[] generatorCode(List<TableDTO> tableDTOList) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        for(TableDTO tableDto : tableDTOList){
            //查询表信息
            Map<String, String> table = queryTable(tableDto.getTableName());
            //查询列信息
            List<Map<String, String>> columns = queryColumns(tableDto.getTableName());
            //生成代码
            GenUtils.generatorCode(table, columns, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}

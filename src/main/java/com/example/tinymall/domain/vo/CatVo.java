package com.example.tinymall.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName CatVo
 * @Description
 * @Author jzf
 * @Date 2020-4-30 16:15
 */
@Data
public class CatVo {
    private Integer value = null;
    private String label = null;
    private List children = null;
}

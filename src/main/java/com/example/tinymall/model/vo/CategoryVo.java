package com.example.tinymall.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName CategoryVo
 * @Description 商品分类
 * @Author jzf
 * @Date 2020-5-12 16:07
 */
@Data
public class CategoryVo {
    private Integer id;
    private String name;
    private String keywords;
    private String desc;
    private String iconUrl;
    private String picUrl;
    private String level;
    private List<CategoryVo> children;
}

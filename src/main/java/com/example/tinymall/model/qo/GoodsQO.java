package com.example.tinymall.model.qo;

import lombok.Data;

/**
 * @ClassName GoodsQO
 * @Description 商品查询条件
 * @Author jzf
 * @Date 2020-5-9 17:16
 */
@Data
public class GoodsQO {
    private Integer categoryId;
    private Integer brandId;
    private String keyword;
    private Boolean isNew;
    private Boolean isHot;
}

package com.example.tinymall.model.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName FootprintVO
 * @Description 用户足迹
 * @Author jzf
 * @Date 2020-6-8 16:16
 */
@Getter
@Setter
public class FootprintVO {
    private Long footPrintId;
    private Long goodsId;
    private String createTime;
    private String goodsName;
    private String brief;
    private String picUrl;
    private Long retailPrice;
}

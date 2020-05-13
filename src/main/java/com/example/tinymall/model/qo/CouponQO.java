package com.example.tinymall.model.qo;

import lombok.Data;

/**
 * @description: 优惠券查询条件
 * @author: fang
 * @create: 2020-05-13 23:26
 **/
@Data
public class CouponQO {
    private String name;
    private Short type;
    private Short status;
    private Integer userId;
}

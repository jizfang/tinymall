package com.example.tinymall.model.qo;

import lombok.Data;

import java.util.List;

/**
 * @description: 订单查询条件
 * @author: fang
 * @create: 2020-05-12 23:05
 **/
@Data
public class OrderQO {
    private Integer userId;
    private String orderSn;
    private Long start;
    private Long end;
    private List<Short> orderStatusArray;
}

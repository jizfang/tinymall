package com.example.tinymall.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @ClassName CouponVo
 * @Description
 * @Author jzf
 * @Date 2020-4-15 20:00
 */
@Getter
@Setter
public class CouponVo {
    private Integer id;
    private Integer cid;
    private String name;
    private String desc;
    private String tag;
    private BigDecimal min;
    private BigDecimal discount;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean available;
}

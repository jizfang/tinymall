package com.example.tinymall.model.vo;

import com.example.tinymall.entity.TinymallOrderGoods;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName AfterSaleVO
 * @Description
 * @Author jzf
 * @Date 2020-6-11 16:50
 */
@Getter
@Setter
public class AfterSaleVO {
    /**
     * 售后编号
     */
    private String aftersaleSn;

    private Integer afterSaleId;

    /**
     * 退款金额
     */
    private BigDecimal amount;

    List<TinymallOrderGoods> orderGoodsList;
}

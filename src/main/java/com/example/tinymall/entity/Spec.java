package com.example.tinymall.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;

import com.example.tinymall.model.po.BasePO;
import com.example.tinymall.mybatis.JsonStringArrayTypeHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.ColumnType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 规格表
 *
 * @author fang
 * @email jizhongfang@gmail.com
 * @date 2020-07-23 10:12:58
 */
@Getter
@Setter
@ToString
@Table(name = "tinymall_spec")
@ApiModel(value="Spec", description="规格表")
public class Spec extends BasePO<Integer> {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value="ID")
    private Integer id;
    /**
     * 名称
     */
    @Column(name = "name")
    @ApiModelProperty(value="名称")
    private String name;
    /**
     * 规格选项
     */
    @Column(name = "options")
    @ApiModelProperty(value="规格选项")
    private String options;
    /**
     * 排序
     */
    @Column(name = "seq")
    @ApiModelProperty(value="排序")
    private Integer seq;
    /**
     * 模板ID
     */
    @Column(name = "template_id")
    @ApiModelProperty(value="模板ID")
    private Integer templateId;
}
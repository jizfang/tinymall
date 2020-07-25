package com.example.tinymall.entity;

import javax.persistence.*;

import com.example.tinymall.model.po.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_template")
@ApiModel(value="TinymallTemplate", description="模板表")
public class TinymallTemplate extends BasePO<Integer> {
    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value="ID")
    private Integer id;

    /**
     * 模板名称
     */
    @Column(name = "name")
    @ApiModelProperty(value="模板名称")
    private String name;

    /**
     * 规格数量
     */
    @Column(name = "spec_num")
    @ApiModelProperty(value="规格数量")
    private Integer specNum;

    /**
     * 参数数量
     */
    @Column(name = "para_num")
    @ApiModelProperty(value="参数数量")
    private Integer paraNum;
}
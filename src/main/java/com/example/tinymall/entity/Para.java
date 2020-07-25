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
 * 参数表
 *
 * @author fang
 * @email jizhongfang@gmail.com
 * @date 2020-07-23 10:12:58
 */
@Getter
@Setter
@ToString
@Table(name = "tinymall_para")
@ApiModel(value="Para", description="参数表")
public class Para extends BasePO<Integer> {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value="id")
    private Integer id;
    /**
     * 名称
     */
    @Column(name = "name")
    @ApiModelProperty(value="名称")
    private String name;
    /**
     * 选项
     */
    @Column(name = "options")
    @ApiModelProperty(value="选项")
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
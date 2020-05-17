package com.example.tinymall.model.qo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: fang
 * @create: 2020-05-18 06:11
 **/
@Data
public class CommentQO {
    private Byte type;
    private Integer valueId;
    private Integer showType;
}

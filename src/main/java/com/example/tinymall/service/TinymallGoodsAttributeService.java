package com.example.tinymall.service;

import java.util.List;

/**
 * @ClassName TinymallGoodsAttributeService
 * @Description
 * @Author jzf
 * @Date 2020-4-14 21:01
 */
public interface TinymallGoodsAttributeService {

    List queryByGid(Integer goodsId);
}

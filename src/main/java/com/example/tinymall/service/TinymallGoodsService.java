package com.example.tinymall.service;

import com.example.tinymall.common.mineservice.BaseService;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.entity.TinymallGoods;
import com.example.tinymall.model.dto.GoodsDTO;

import java.util.List;

/**
 * @ClassName TinymallGoodsService
 * @Description
 * @Author jzf
 * @Date 2020-4-10 16:09
 */
public interface TinymallGoodsService extends BaseService<TinymallGoods,Integer> {
    List<TinymallGoods> queryByNew(int offset, int limit);

    List<TinymallGoods> queryByHot(int offset, int limit);

    List<TinymallGoods> queryByCategory(List<Integer> l2List, int i, Integer catlogMoreLimit);

    List<TinymallGoods> queryByCategory(Integer catId, int offset, int limit);

    Integer queryOnSale();

    List<Integer> getCatIds(Integer brandId, String keyword, Boolean isHot, Boolean isNew);

    Object update(GoodsDTO goodsAllinone);

    Object delete(TinymallGoods goods);

    Object create(GoodsDTO goodsAllinone);

    Object detail(Integer id);

    Object list2();
}

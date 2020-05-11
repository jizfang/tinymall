package com.example.tinymall.service;

import com.example.tinymall.common.mineservice.BaseService;
import com.example.tinymall.entity.TinymallCart;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName LitemallCartService
 * @Description
 * @Author jzf
 * @Date 2020-4-15 14:19
 */
public interface TinymallCartService extends BaseService<TinymallCart,Integer> {
    TinymallCart queryExist(Integer goodsId, Integer productId, Integer userId);

    List<TinymallCart> queryByUid(int userId);


    List<TinymallCart> queryByUidAndChecked(Integer userId);

    int delete(List<Integer> productIdList, int userId);


    TinymallCart findById(Integer userId, Integer id);

    int updateCheck(Integer userId, List<Integer> idsList, Boolean checked);

    void clearGoods(Integer userId);

    void updateProduct(Integer id, String goodsSn, String goodsName, BigDecimal price, String url);
}

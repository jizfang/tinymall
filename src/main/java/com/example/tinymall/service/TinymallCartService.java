package com.example.tinymall.service;

import com.example.tinymall.domain.TinymallCart;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName LitemallCartService
 * @Description
 * @Author jzf
 * @Date 2020-4-15 14:19
 */
public interface TinymallCartService {
    TinymallCart queryExist(Integer goodsId, Integer productId, Integer userId);

    void add(TinymallCart cart);

    int updateById(TinymallCart cart);

    List<TinymallCart> queryByUid(int userId);


    List<TinymallCart> queryByUidAndChecked(Integer userId);

    int delete(List<Integer> productIdList, int userId);

    TinymallCart findById(Integer id);

    TinymallCart findById(Integer userId, Integer id);

    int updateCheck(Integer userId, List<Integer> idsList, Boolean checked);

    void clearGoods(Integer userId);

    List<TinymallCart> querySelective(Integer userId, Integer goodsId, Integer page, Integer limit, String sort, String order);

    void deleteById(Integer id);

    boolean checkExist(Integer goodsId);

    void updateProduct(Integer id, String goodsSn, String goodsName, BigDecimal price, String url);
}

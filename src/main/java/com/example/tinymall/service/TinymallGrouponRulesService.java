package com.example.tinymall.service;

import com.example.tinymall.domain.TinymallGrouponRules;

import java.util.List;

/**
 * @ClassName TinymallGrouponRulesService
 * @Description
 * @Author jzf
 * @Date 2020-4-15 11:53
 */
public interface TinymallGrouponRulesService {
    int createRules(TinymallGrouponRules rules);

    /**
     * 根据ID查找对应团购项
     *
     * @param id
     * @return
     */
    TinymallGrouponRules findById(Integer id);

    /**
     * 查询某个商品关联的团购规则
     *
     * @param goodsId
     * @return
     */
    List<TinymallGrouponRules> queryByGoodsId(Integer goodsId);

    int countByGoodsId(Integer goodsId);

    List<TinymallGrouponRules> queryByStatus(Short status);

    /**
     * 获取首页团购规则列表
     *
     * @param page
     * @param limit
     * @return
     */
    List<TinymallGrouponRules> queryList(Integer page, Integer limit);

    List<TinymallGrouponRules> queryList(Integer page, Integer limit, String sort, String order);

    /**
     * 判断某个团购规则是否已经过期
     *
     * @return
     */
    boolean isExpired(TinymallGrouponRules rules);
    /**
     * 获取团购规则列表
     *
     * @param goodsId
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    List<TinymallGrouponRules> querySelective(String goodsId, Integer page, Integer size, String sort, String order);

    void delete(Integer id);

    int updateById(TinymallGrouponRules grouponRules);
}

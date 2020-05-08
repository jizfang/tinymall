package com.example.tinymall.service;

import com.example.tinymall.entity.TinymallComment;

import java.util.List;

/**
 * @ClassName TinymallCommentService
 * @Description
 * @Author jzf
 * @Date 2020-4-15 11:47
 */
public interface TinymallCommentService {

    List<TinymallComment> queryGoodsByGid(Integer id, int offset, int limit);

    List<TinymallComment> query(Byte type, Integer valueId, Integer showType, Integer offset, Integer limit);

    int count(Byte type, Integer valueId, Integer showType);

    public int save(TinymallComment comment);

    List<TinymallComment> querySelective(String userId, String valueId, Integer page, Integer size, String sort, String order);

    void deleteById(Integer id);

    TinymallComment findById(Integer id);

    int updateById(TinymallComment comment);
}

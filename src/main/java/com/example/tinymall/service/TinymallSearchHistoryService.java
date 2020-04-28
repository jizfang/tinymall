package com.example.tinymall.service;

import com.example.tinymall.domain.TinymallSearchHistory;

import java.util.List;

/**
 * @ClassName TinymallSearchHistoryService
 * @Description
 * @Author jzf
 * @Date 2020-4-14 17:43
 */
public interface TinymallSearchHistoryService {
    void save(TinymallSearchHistory searchHistory);

    List<TinymallSearchHistory> queryByUid(int uid);

    void deleteByUid(int uid);

    List<TinymallSearchHistory> querySelective(String userId, String keyword, Integer page, Integer size, String sort, String order);
}

package com.example.tinymall.service;

import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.entity.TinymallSearchHistory;

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

    PageVO<TinymallSearchHistory> querySelective(PageQO pageQO);
}

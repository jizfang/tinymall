package com.example.tinymall.service;

import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.entity.TinymallKeyword;

import java.util.List;

/**
 * @ClassName TinymallKeywordService
 * @Description 关键词
 * @Author jzf
 * @Date 2020-5-6 11:06
 */
public interface TinymallKeywordService {
    TinymallKeyword queryDefault();

    List<TinymallKeyword> queryHots();

    PageVO<TinymallKeyword> querySelective(PageQO pageQO);

    void add(TinymallKeyword keywords);

    TinymallKeyword findById(Integer id);

    int updateById(TinymallKeyword keywords);

    void deleteById(Integer id);
}

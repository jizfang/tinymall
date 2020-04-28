package com.example.tinymall.service;

import com.example.tinymall.domain.TinymallIssue;

import java.util.List;

/**
 * @ClassName TinymallIssueService
 * @Description
 * @Author jzf
 * @Date 2020-4-15 11:31
 */
public interface TinymallIssueService {
    void deleteById(Integer id);

    void add(TinymallIssue issue);

    List<TinymallIssue> querySelective(String question, Integer page, Integer limit, String sort, String order);

    int updateById(TinymallIssue issue);

    TinymallIssue findById(Integer id);
}

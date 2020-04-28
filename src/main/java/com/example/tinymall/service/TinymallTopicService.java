package com.example.tinymall.service;

import com.example.tinymall.domain.TinymallTopic;

import java.util.List;

/**
 * @ClassName TinymallTopicService
 * @Description
 * @Author jzf
 * @Date 2020-4-10 16:09
 */
public interface TinymallTopicService {
    List<TinymallTopic> queryList(int offset, int limit);

    List<TinymallTopic> queryList(int offset, int limit, String sort, String order);
}

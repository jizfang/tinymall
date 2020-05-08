package com.example.tinymall.service.impl;

import com.example.tinymall.entity.TinymallTopic;
import com.example.tinymall.mapper.TinymallTopicMapper;
import com.example.tinymall.service.TinymallTopicService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TinymallTopicServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-10 16:24
 */
@Service
public class TinymallTopicServiceImpl implements TinymallTopicService {

    @Resource
    private TinymallTopicMapper topicMapper;

    @Override
    public List<TinymallTopic> queryList(int offset, int limit) {
        return queryList(offset, limit, "add_time", "desc");
    }

    @Override
    public List<TinymallTopic> queryList(int offset, int limit, String sort, String order) {
        /*TinymallTopicExample example = new TinymallTopicExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause(sort + " " + order);
        PageHelper.startPage(offset, limit);
        return topicMapper.selectByExampleSelective(example, columns);*/
        return Lists.newArrayList();
    }
}

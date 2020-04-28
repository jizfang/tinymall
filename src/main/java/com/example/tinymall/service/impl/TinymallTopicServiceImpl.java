package com.example.tinymall.service.impl;

import com.example.tinymall.dao.TinymallTopicMapper;
import com.example.tinymall.domain.TinymallTopic;
import com.example.tinymall.domain.TinymallTopicExample;
import com.example.tinymall.service.TinymallTopicService;
import com.github.pagehelper.PageHelper;
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
    private TinymallTopic.Column[] columns = new TinymallTopic.Column[]{TinymallTopic.Column.id, TinymallTopic.Column.title,
            TinymallTopic.Column.subtitle, TinymallTopic.Column.price, TinymallTopic.Column.picUrl, TinymallTopic.Column.readCount};

    @Override
    public List<TinymallTopic> queryList(int offset, int limit) {
        return queryList(offset, limit, "add_time", "desc");
    }

    @Override
    public List<TinymallTopic> queryList(int offset, int limit, String sort, String order) {
        TinymallTopicExample example = new TinymallTopicExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause(sort + " " + order);
        PageHelper.startPage(offset, limit);
        return topicMapper.selectByExampleSelective(example, columns);
    }
}

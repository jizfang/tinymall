package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
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
public class TinymallTopicServiceImpl extends BaseMySqlServiceImpl<TinymallTopic,Integer> implements TinymallTopicService {

}

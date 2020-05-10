package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.entity.TinymallIssue;
import com.example.tinymall.mapper.TinymallIssueMapper;
import com.example.tinymall.service.TinymallIssueService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName TinymallIssueServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-15 11:33
 */
@Service
public class TinymallIssueServiceImpl extends BaseMySqlServiceImpl<TinymallIssue,Integer> implements TinymallIssueService {
}

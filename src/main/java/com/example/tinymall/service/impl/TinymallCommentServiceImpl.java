package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.entity.TinymallComment;
import com.example.tinymall.mapper.TinymallCommentMapper;
import com.example.tinymall.service.TinymallCommentService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName TinymallCommentServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-15 11:49
 */
@Service
public class TinymallCommentServiceImpl extends BaseMySqlServiceImpl<TinymallComment,Integer> implements TinymallCommentService {
}

package com.example.tinymall.service.impl;

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
public class TinymallCommentServiceImpl implements TinymallCommentService {
    @Resource
    private TinymallCommentMapper commentMapper;

    @Override
    public List<TinymallComment> queryGoodsByGid(Integer id, int offset, int limit) {
        /*TinymallCommentExample example = new TinymallCommentExample();
        example.setOrderByClause(TinymallComment.Column.addTime.desc());
        example.or().andValueIdEqualTo(id).andTypeEqualTo((byte) 0).andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return commentMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public List<TinymallComment> query(Byte type, Integer valueId, Integer showType, Integer offset, Integer limit) {
        /*TinymallCommentExample example = new TinymallCommentExample();
        example.setOrderByClause(TinymallComment.Column.addTime.desc());
        if (showType == 0) {
            example.or().andValueIdEqualTo(valueId).andTypeEqualTo(type).andDeletedEqualTo(false);
        } else if (showType == 1) {
            example.or().andValueIdEqualTo(valueId).andTypeEqualTo(type).andHasPictureEqualTo(true).andDeletedEqualTo(false);
        } else {
            throw new RuntimeException("showType不支持");
        }
        PageHelper.startPage(offset, limit);
        return commentMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public int count(Byte type, Integer valueId, Integer showType) {
        /*TinymallCommentExample example = new TinymallCommentExample();
        if (showType == 0) {
            example.or().andValueIdEqualTo(valueId).andTypeEqualTo(type).andDeletedEqualTo(false);
        } else if (showType == 1) {
            example.or().andValueIdEqualTo(valueId).andTypeEqualTo(type).andHasPictureEqualTo(true).andDeletedEqualTo(false);
        } else {
            throw new RuntimeException("showType不支持");
        }
        return (int) commentMapper.countByExample(example);*/
        return 0;
    }

    @Override
    public int save(TinymallComment comment) {
        comment.setAddTime(LocalDateTime.now());
        comment.setUpdateTime(LocalDateTime.now());
        return commentMapper.insertSelective(comment);
    }

    @Override
    public List<TinymallComment> querySelective(String userId, String valueId, Integer page, Integer size, String sort, String order) {
        /*TinymallCommentExample example = new TinymallCommentExample();
        TinymallCommentExample.Criteria criteria = example.createCriteria();

        // type=2 是订单商品回复，这里过滤
        criteria.andTypeNotEqualTo((byte) 2);

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(valueId)) {
            criteria.andValueIdEqualTo(Integer.valueOf(valueId)).andTypeEqualTo((byte) 0);
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return commentMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public void deleteById(Integer id) {
        commentMapper.deleteByIds(String.valueOf(id));
    }

    @Override
    public TinymallComment findById(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(TinymallComment comment) {
        return commentMapper.updateByPrimaryKeySelective(comment);
    }
}

package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.entity.TinymallCollect;
import com.example.tinymall.mapper.TinymallCollectMapper;
import com.example.tinymall.service.TinymallCollectService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName TinymallCollectServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-15 12:00
 */
@Service
public class TinymallCollectServiceImpl extends BaseMySqlServiceImpl<TinymallCollect,Integer> implements TinymallCollectService {
    @Resource
    private TinymallCollectMapper collectMapper;

    @Override
    public int count(int uid, Integer gid) {
        TinymallCollect collect = new TinymallCollect();
        collect.setUserId(uid);
        collect.setValueId(gid);
        collect.setDeleted(0);
        return (int) collectMapper.selectCount(collect);
    }

    @Override
    public List<TinymallCollect> queryByType(Integer userId, Byte type, Integer page, Integer limit, String sort, String order) {
        /*TinymallCollectExample example = new TinymallCollectExample();
        TinymallCollectExample.Criteria criteria = example.createCriteria();

        if (type != null) {
            criteria.andTypeEqualTo(type);
        }
        criteria.andUserIdEqualTo(userId);
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return collectMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public int countByType(Integer userId, Byte type) {
        /*TinymallCollectExample example = new TinymallCollectExample();
        example.or().andUserIdEqualTo(userId).andTypeEqualTo(type).andDeletedEqualTo(false);
        return (int) collectMapper.countByExample(example);*/
        return 0;
    }

    @Override
    public TinymallCollect queryByTypeAndValue(Integer userId, Byte type, Integer valueId) {
        /*TinymallCollectExample example = new TinymallCollectExample();
        example.or().andUserIdEqualTo(userId).andValueIdEqualTo(valueId).andTypeEqualTo(type).andDeletedEqualTo(false);
        return collectMapper.selectOneByExample(example);*/
        return null;
    }
}

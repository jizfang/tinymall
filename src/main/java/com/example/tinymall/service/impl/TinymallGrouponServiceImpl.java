package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.core.constants.GrouponConstant;
import com.example.tinymall.entity.TinymallGroupon;
import com.example.tinymall.mapper.TinymallGrouponMapper;
import com.example.tinymall.service.TinymallGrouponService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName TinymallGrouponServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-23 11:11
 */
@Service
public class TinymallGrouponServiceImpl extends BaseMySqlServiceImpl<TinymallGroupon,Integer> implements TinymallGrouponService {
    @Resource
    private TinymallGrouponMapper mapper;

    @Override
    public List<TinymallGroupon> queryMyGroupon(Integer userId) {
        /*TinymallGrouponExample example = new TinymallGrouponExample();
        example.or().andUserIdEqualTo(userId).andCreatorUserIdEqualTo(userId).andGrouponIdEqualTo(0).andStatusNotEqualTo(GrouponConstant.STATUS_NONE).andDeletedEqualTo(false);
        example.orderBy("add_time desc");
        return mapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public List<TinymallGroupon> queryMyJoinGroupon(Integer userId) {
        /*TinymallGrouponExample example = new TinymallGrouponExample();
        example.or().andUserIdEqualTo(userId).andGrouponIdNotEqualTo(0).andStatusNotEqualTo(GrouponConstant.STATUS_NONE).andDeletedEqualTo(false);
        example.orderBy("add_time desc");
        return mapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public TinymallGroupon queryByOrderId(Integer orderId) {
        /*TinymallGrouponExample example = new TinymallGrouponExample();
        example.or().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        return mapper.selectOneByExample(example);*/
        return null;
    }

    @Override
    public List<TinymallGroupon> queryJoinRecord(Integer id) {
        /*TinymallGrouponExample example = new TinymallGrouponExample();
        example.or().andGrouponIdEqualTo(id).andStatusNotEqualTo(GrouponConstant.STATUS_NONE).andDeletedEqualTo(false);
        example.orderBy("add_time desc");
        return mapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public TinymallGroupon queryById(Integer userId, Integer id) {
        /*TinymallGrouponExample example = new TinymallGrouponExample();
        example.or().andIdEqualTo(id).andUserIdEqualTo(id).andDeletedEqualTo(false);
        return mapper.selectOneByExample(example);*/
        return null;
    }

    @Override
    public int countGroupon(Integer grouponId) {
        /*TinymallGrouponExample example = new TinymallGrouponExample();
        example.or().andGrouponIdEqualTo(grouponId).andStatusNotEqualTo(GrouponConstant.STATUS_NONE).andDeletedEqualTo(false);
        return (int) mapper.countByExample(example);*/
        return 0;
    }

    @Override
    public boolean hasJoin(Integer userId, Integer grouponId) {
        /*TinymallGrouponExample example = new TinymallGrouponExample();
        example.or().andUserIdEqualTo(userId).andGrouponIdEqualTo(grouponId).andStatusNotEqualTo(GrouponConstant.STATUS_NONE).andDeletedEqualTo(false);
        return mapper.countByExample(example) != 0;*/
        return true;
    }
}

package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.entity.TinymallCollect;
import com.example.tinymall.mapper.TinymallCollectMapper;
import com.example.tinymall.model.vo.FootprintVO;
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
    public List<FootprintVO> queryByType(TinymallCollect condition) {
        return collectMapper.queryByType(condition);
    }

    @Override
    public TinymallCollect queryByTypeAndValue(Integer userId, Byte type, Integer valueId) {
        /*TinymallCollectExample example = new TinymallCollectExample();
        example.or().andUserIdEqualTo(userId).andValueIdEqualTo(valueId).andTypeEqualTo(type).andDeletedEqualTo(false);
        return collectMapper.selectOneByExample(example);*/
        return null;
    }
}

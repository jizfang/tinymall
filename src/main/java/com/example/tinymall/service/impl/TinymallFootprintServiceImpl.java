package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.core.utils.DateTimeUtil;
import com.example.tinymall.entity.TinymallFootprint;
import com.example.tinymall.mapper.TinymallFootprintMapper;
import com.example.tinymall.model.vo.FootprintVO;
import com.example.tinymall.service.TinymallFootprintService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName TinymallFootprintServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-15 12:07
 */
@Service
public class TinymallFootprintServiceImpl extends BaseMySqlServiceImpl<TinymallFootprint,Integer> implements TinymallFootprintService {
    @Resource
    private TinymallFootprintMapper footprintMapper;

    @Override
    public List<TinymallFootprint> queryByAddTime(Integer userId, Integer page, Integer size) {
        /*TinymallFootprintExample example = new TinymallFootprintExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        example.setOrderByClause(TinymallFootprint.Column.addTime.desc());
        PageHelper.startPage(page, size);
        return footprintMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public List<FootprintVO> selectFootprintPage(TinymallFootprint condition) {
        List<FootprintVO> resultList = footprintMapper.selectFootprintPage(condition);
        return resultList;
    }
}

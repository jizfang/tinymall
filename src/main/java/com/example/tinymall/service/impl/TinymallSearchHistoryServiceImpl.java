package com.example.tinymall.service.impl;

import com.example.tinymall.dao.TinymallSearchHistoryMapper;
import com.example.tinymall.domain.TinymallSearchHistory;
import com.example.tinymall.domain.TinymallSearchHistoryExample;
import com.example.tinymall.service.TinymallSearchHistoryService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName TinymallSearchHistoryServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-14 17:44
 */
@Service
public class TinymallSearchHistoryServiceImpl implements TinymallSearchHistoryService {

    @Resource
    private TinymallSearchHistoryMapper searchHistoryMapper;

    @Override
    public void save(TinymallSearchHistory searchHistory) {
        searchHistory.setAddTime(LocalDateTime.now());
        searchHistory.setUpdateTime(LocalDateTime.now());
        searchHistoryMapper.insertSelective(searchHistory);
    }

    @Override
    public List<TinymallSearchHistory> queryByUid(int uid) {
        TinymallSearchHistoryExample example = new TinymallSearchHistoryExample();
        example.or().andUserIdEqualTo(uid).andDeletedEqualTo(false);
        example.setDistinct(true);
        return searchHistoryMapper.selectByExampleSelective(example, TinymallSearchHistory.Column.keyword);
    }

    @Override
    public void deleteByUid(int uid) {
        TinymallSearchHistoryExample example = new TinymallSearchHistoryExample();
        example.or().andUserIdEqualTo(uid);
        searchHistoryMapper.logicalDeleteByExample(example);
    }

    @Override
    public List<TinymallSearchHistory> querySelective(String userId, String keyword, Integer page, Integer size, String sort, String order) {
        TinymallSearchHistoryExample example = new TinymallSearchHistoryExample();
        TinymallSearchHistoryExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andKeywordLike("%" + keyword + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return searchHistoryMapper.selectByExample(example);
    }
}

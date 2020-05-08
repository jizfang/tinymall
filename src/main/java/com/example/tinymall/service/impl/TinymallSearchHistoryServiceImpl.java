package com.example.tinymall.service.impl;

import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.entity.TinymallSearchHistory;
import com.example.tinymall.mapper.TinymallSearchHistoryMapper;
import com.example.tinymall.model.bo.SearchHistoryCondition;
import com.example.tinymall.service.TinymallSearchHistoryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
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
        /*TinymallSearchHistoryExample example = new TinymallSearchHistoryExample();
        example.or().andUserIdEqualTo(uid).andDeletedEqualTo(false);
        example.setDistinct(true);
        return searchHistoryMapper.selectByExampleSelective(example, TinymallSearchHistory.Column.keyword);*/
        return Lists.newArrayList();
    }

    @Override
    public void deleteByUid(int uid) {
        /*TinymallSearchHistoryExample example = new TinymallSearchHistoryExample();
        example.or().andUserIdEqualTo(uid);
        searchHistoryMapper.logicalDeleteByExample(example);*/
    }

    @Override
    public PageVO<TinymallSearchHistory> querySelective(PageQO pageQO) {
        /*TinymallSearchHistoryExample example = new TinymallSearchHistoryExample();
        TinymallSearchHistoryExample.Criteria criteria = example.createCriteria();
        SearchHistoryCondition condition = (SearchHistoryCondition) pageQO.getCondition();
        if (!StringUtils.isEmpty(condition.getUserId())) {
            criteria.andUserIdEqualTo(Integer.valueOf(condition.getUserId()));
        }
        if (!StringUtils.isEmpty(condition.getKeyword())) {
            criteria.andKeywordLike("%" + condition.getKeyword() + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(pageQO.getOrderBy())) {
            example.setOrderByClause(pageQO.getOrderBy());
        }

        Page page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize());
        searchHistoryMapper.selectByExample(example);
        return PageVO.build(page);*/
        return null;
    }
}

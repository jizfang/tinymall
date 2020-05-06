package com.example.tinymall.service.impl;

import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.dao.TinymallKeywordMapper;
import com.example.tinymall.domain.TinymallKeyword;
import com.example.tinymall.domain.TinymallKeywordExample;
import com.example.tinymall.domain.bo.KeywordCondition;
import com.example.tinymall.service.TinymallKeywordService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName TinymallKeywordService
 * @Description
 * @Author jzf
 * @Date 2020-5-6 11:12
 */
@Service
public class TinymallKeywordServiceImpl implements TinymallKeywordService {

    @Resource
    private TinymallKeywordMapper keywordsMapper;

    @Override
    public TinymallKeyword queryDefault() {
        TinymallKeywordExample example = new TinymallKeywordExample();
        example.or().andIsDefaultEqualTo(true).andDeletedEqualTo(false);
        return keywordsMapper.selectOneByExample(example);
    }

    @Override
    public List<TinymallKeyword> queryHots() {
        TinymallKeywordExample example = new TinymallKeywordExample();
        example.or().andIsHotEqualTo(true).andDeletedEqualTo(false);
        return keywordsMapper.selectByExample(example);
    }

    @Override
    public PageVO<TinymallKeyword> querySelective(PageQO pageQO) {
        TinymallKeywordExample example = new TinymallKeywordExample();
        TinymallKeywordExample.Criteria criteria = example.createCriteria();
        KeywordCondition condition = (KeywordCondition) pageQO.getCondition();
        if (!StringUtils.isEmpty(condition.getKeyword())) {
            criteria.andKeywordLike("%" + condition.getKeyword() + "%");
        }
        if (!StringUtils.isEmpty(condition.getUrl())) {
            criteria.andUrlLike("%" + condition.getUrl() + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(pageQO.getOrderBy())) {
            example.setOrderByClause(pageQO.getOrderBy());
        }

        Page page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize());
        keywordsMapper.selectByExample(example);
        return PageVO.build(page);
    }

    @Override
    public void add(TinymallKeyword keywords) {
        keywords.setAddTime(LocalDateTime.now());
        keywords.setUpdateTime(LocalDateTime.now());
        keywordsMapper.insertSelective(keywords);
    }

    @Override
    public TinymallKeyword findById(Integer id) {
        return keywordsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(TinymallKeyword keywords) {
        keywords.setUpdateTime(LocalDateTime.now());
        return keywordsMapper.updateByPrimaryKeySelective(keywords);
    }

    @Override
    public void deleteById(Integer id) {
        keywordsMapper.logicalDeleteByPrimaryKey(id);
    }
}

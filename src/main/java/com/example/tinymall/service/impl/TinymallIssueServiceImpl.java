package com.example.tinymall.service.impl;

import com.example.tinymall.dao.TinymallIssueMapper;
import com.example.tinymall.domain.TinymallIssue;
import com.example.tinymall.domain.TinymallIssueExample;
import com.example.tinymall.service.TinymallIssueService;
import com.github.pagehelper.PageHelper;
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
public class TinymallIssueServiceImpl implements TinymallIssueService {
    @Resource
    private TinymallIssueMapper issueMapper;

    @Override
    public void deleteById(Integer id) {
        issueMapper.logicalDeleteByPrimaryKey(id);
    }

    @Override
    public void add(TinymallIssue issue) {
        issue.setAddTime(LocalDateTime.now());
        issue.setUpdateTime(LocalDateTime.now());
        issueMapper.insertSelective(issue);
    }

    @Override
    public List<TinymallIssue> querySelective(String question, Integer page, Integer limit, String sort, String order) {
        TinymallIssueExample example = new TinymallIssueExample();
        TinymallIssueExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(question)) {
            criteria.andQuestionLike("%" + question + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return issueMapper.selectByExample(example);
    }

    @Override
    public int updateById(TinymallIssue issue) {
        issue.setUpdateTime(LocalDateTime.now());
        return issueMapper.updateByPrimaryKeySelective(issue);
    }

    @Override
    public TinymallIssue findById(Integer id) {
        return issueMapper.selectByPrimaryKey(id);
    }
}

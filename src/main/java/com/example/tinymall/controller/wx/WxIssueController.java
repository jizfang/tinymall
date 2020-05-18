package com.example.tinymall.controller.wx;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.entity.TinymallIssue;
import com.example.tinymall.service.TinymallIssueService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName WxIssueController
 * @Description
 * @Author jzf
 * @Date 2020-5-18 9:07
 */
@ResponseResult
@RestController
@RequestMapping("/wx/issue")
@Validated
public class WxIssueController {
    @Autowired
    private TinymallIssueService issueService;

    /**
     * 帮助中心
     */
    @RequestMapping("/list")
    public Object list(String question, PageQO page) {
        if(StringUtils.isNotBlank(question)){
            TinymallIssue condition = new TinymallIssue();
            condition.setQuestion(question);
        }
        PageVO<TinymallIssue> issueList = issueService.selectPage(page);
        return issueList;
    }
}

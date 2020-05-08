package com.example.tinymall.controller.wx;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.common.result.CommonResult;
import com.example.tinymall.entity.TinymallKeyword;
import com.example.tinymall.entity.TinymallSearchHistory;
import com.example.tinymall.model.bo.KeywordCondition;
import com.example.tinymall.model.bo.LoginUser;
import com.example.tinymall.service.TinymallKeywordService;
import com.example.tinymall.service.TinymallSearchHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName WxSearchController
 * @Description 商品搜索
 * @Author jzf
 * @Date 2020-5-6 10:56
 */
@RestController
@ResponseResult
@RequestMapping("/wx/search")
@Slf4j
public class WxSearchController {
    @Autowired
    private TinymallKeywordService keywordsService;
    @Autowired
    private TinymallSearchHistoryService searchHistoryService;

    /**
     * 搜索页面信息
     * <p>
     * 如果用户已登录，则给出用户历史搜索记录；
     * 如果没有登录，则给出空历史搜索记录。
     *
     * @return 搜索页面信息
     */
    @GetMapping("index")
    public Object index() {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        Integer userId = Integer.valueOf(loginUser.getId());
        //取出输入框默认的关键词
        TinymallKeyword defaultKeyword = keywordsService.queryDefault();
        //取出热闹关键词
        List<TinymallKeyword> hotKeywordList = keywordsService.queryHots();

        List<TinymallSearchHistory> historyList = null;
        if (userId != null) {
            //取出用户历史关键字
            historyList = searchHistoryService.queryByUid(userId);
        } else {
            historyList = new ArrayList<>(0);
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("defaultKeyword", defaultKeyword);
        data.put("historyKeywordList", historyList);
        data.put("hotKeywordList", hotKeywordList);
        return data;
    }

    /**
     * 关键字提醒
     * <p>
     * 当用户输入关键字一部分时，可以推荐系统中合适的关键字。
     *
     * @param keyword 关键字
     * @return 合适的关键字
     */
    @GetMapping("helper")
    public Object helper(String keyword) {
        PageQO pageQO = new PageQO(1,10000);
        KeywordCondition condition = new KeywordCondition();
        condition.setKeyword(keyword);
        pageQO.setCondition(condition);
        PageVO<TinymallKeyword> keywords = keywordsService.querySelective(pageQO);
        List<TinymallKeyword> keywordsList = keywords.getList();
        String[] keys = new String[keywordsList.size()];
        int index = 0;
        for (TinymallKeyword key : keywordsList) {
            keys[index++] = key.getKeyword();
        }
        return keys;
    }

    /**
     * 清除用户搜索历史
     *
     * @return 清理是否成功
     */
    @PostMapping("clearhistory")
    public Object clearhistory() {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        Integer userId = Integer.valueOf(loginUser.getId());
        searchHistoryService.deleteByUid(userId);
        return CommonResult.success();
    }
}

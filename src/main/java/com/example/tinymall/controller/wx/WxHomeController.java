package com.example.tinymall.controller.wx;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.core.system.SystemConfig;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.entity.*;
import com.example.tinymall.model.bo.LoginUser;
import com.example.tinymall.model.po.Ad;
import com.example.tinymall.service.*;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @ClassName WxHomeController
 * @Description
 * @Author jzf
 * @Date 2020-4-10 16:00
 */
@ResponseResult
@RestController
@RequestMapping("/wx/home")
@Slf4j
public class WxHomeController {

    @Autowired
    private TinymallAdService adService;

    @Autowired
    private TinymallGoodsService goodsService;

    @Autowired
    private TinymallTopicService topicService;

    @Autowired
    private TinymallCategoryService categoryService;

    @Autowired
    private WxGrouponRuleService grouponService;

    /**
     * 首页数据
     * @return 首页数据
     */
    @GetMapping("/index")
    public Object index() {
        Map<String, Object> entity = new HashMap<>();

        List<Ad> bannerList = adService.selectAll();
        entity.put("banner", bannerList);
        List<TinymallCategory> channelList = categoryService.queryChannel();
        entity.put("channel", channelList);
        List<TinymallGoods> newGoodsList = goodsService.queryByNew(0, SystemConfig.getNewLimit());
        entity.put("newGoodsList", newGoodsList);
        List<TinymallTopic> topicList = topicService.selectPage(new PageQO<>(0, SystemConfig.getTopicLimit())).getList();
        entity.put("topicList", topicList);

        return entity;
    }
}

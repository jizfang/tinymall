package com.example.tinymall.controller.wx;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.entity.TinymallGoods;
import com.example.tinymall.entity.TinymallTopic;
import com.example.tinymall.service.TinymallGoodsService;
import com.example.tinymall.service.TinymallTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 专题
 * @author: fang
 * @create: 2020-05-18 05:48
 **/
@ResponseResult
@RestController
@RequestMapping("/wx/topic")
@Validated
public class WxTopicController {
    @Autowired
    private TinymallTopicService topicService;
    @Autowired
    private TinymallGoodsService goodsService;

    /**
     * 专题列表
     *
     * @param pageQO 分页大小
     * @return 专题列表
     */
    @GetMapping("list")
    public Object list(PageQO pageQO) {
        PageVO<TinymallTopic> topicList = topicService.selectPage(pageQO);
        return topicList;
    }

    /**
     * 专题详情
     *
     * @param id 专题ID
     * @return 专题详情
     */
    @GetMapping("detail")
    public Object detail(@NotNull Integer id) {
        TinymallTopic topic = topicService.selectByPk(id);
        List<TinymallGoods> goods = new ArrayList<>();
        for (Integer i : topic.getGoods()) {
            TinymallGoods good = goodsService.selectByPk(i);
            if (null != good)
                goods.add(good);
        }

        Map<String, Object> entity = new HashMap<String, Object>();
        entity.put("topic", topic);
        entity.put("goods", goods);
        return entity;
    }

    /**
     * 相关专题
     *
     * @param id 专题ID
     * @return 相关专题
     */
    @GetMapping("related")
    public Object related(@NotNull Integer id) {
        PageQO page = new PageQO(0,4);
        TinymallTopic condition = new TinymallTopic();
        condition.setId(id);
        condition.setDeleted(0);
        page.setCondition(condition);
        PageVO<TinymallTopic> topicRelatedList = topicService.selectPage(page);
        return topicRelatedList.getList();
    }
}

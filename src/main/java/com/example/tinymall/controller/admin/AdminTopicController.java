package com.example.tinymall.controller.admin;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.core.utils.JacksonUtil;
import com.example.tinymall.entity.TinymallGoods;
import com.example.tinymall.entity.TinymallTopic;
import com.example.tinymall.model.qo.TopicQO;
import com.example.tinymall.service.TinymallGoodsService;
import com.example.tinymall.service.TinymallTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @ClassName AdminTopicController
 * @Description 专题
 * @Author jzf
 * @Date 2020-5-14 8:48
 */
@ResponseResult
@RestController
@RequestMapping("/admin/topic")
@Validated
public class AdminTopicController {
    @Autowired
    private TinymallTopicService topicService;
    @Autowired
    private TinymallGoodsService goodsService;

    @GetMapping("/list")
    public Object list(PageQO pageQO, TopicQO topicQO) {
        pageQO.setCondition(topicQO);
        PageVO<TinymallTopic> topicList = topicService.selectPage(pageQO);
        return topicList;
    }

    @PostMapping("/create")
    public Object create(@RequestBody TinymallTopic topic) {
        topicService.insert(topic);
        return topic;
    }

    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        TinymallTopic topic = topicService.selectByPk(id);
        List<Integer> goodsIds = Arrays.asList(topic.getGoods());
        List<TinymallGoods> goodsList = null;
        if (goodsIds == null || goodsIds.size() == 0) {
            goodsList = new ArrayList<>();
        } else {
            goodsList = goodsService.selectByPks(goodsIds);
        }
        Map<String, Object> data = new HashMap<>(2);
        data.put("topic", topic);
        data.put("goodsList", goodsList);
        return data;
    }

    @PostMapping("/update")
    public Object update(@RequestBody TinymallTopic topic) {
        AssertUtils.isFalse(topicService.updateByPk(topic.getId(),topic) == 0,"需要更新的数据不存在");
        return topic;
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@RequestBody TinymallTopic topic) {
        topicService.deleteByPk(topic.getId());
    }
    
    @PostMapping("/batch-delete")
    @ResponseStatus(HttpStatus.CREATED)
    public void batchDelete(@RequestBody String body) {
        List<Integer> ids = JacksonUtil.parseIntegerList(body, "ids");
        topicService.deleteByPks(ids);
    }
}

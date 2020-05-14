package com.example.tinymall.controller.admin;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.common.result.CommonResult;
import com.example.tinymall.core.constants.GrouponConstant;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.entity.TinymallGoods;
import com.example.tinymall.entity.TinymallGroupon;
import com.example.tinymall.entity.TinymallGrouponRules;
import com.example.tinymall.service.TinymallGoodsService;
import com.example.tinymall.service.TinymallGrouponRulesService;
import com.example.tinymall.service.TinymallGrouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminGrouponController
 * @Description 团购
 * @Author jzf
 * @Date 2020-5-14 14:19
 */
@ResponseResult
@RestController
@RequestMapping("/admin/groupon")
@Validated
@Slf4j
public class AdminGrouponController {
    @Autowired
    private TinymallGrouponRulesService rulesService;
    @Autowired
    private TinymallGoodsService goodsService;
    @Autowired
    private TinymallGrouponService grouponService;
    /*@Autowired
    private TaskService taskService;*/

    @GetMapping("/listRecord")
    public Object listRecord(PageQO pageQO, Integer grouponRuleId) {
        TinymallGroupon condition = new TinymallGroupon();
        if(grouponRuleId != null){
            condition.setGrouponId(grouponRuleId);
            pageQO.setCondition(condition);
        }

        PageVO<TinymallGroupon> grouponList = grouponService.selectPage(pageQO);

        List<Map<String, Object>> groupons = new ArrayList<>();
        for (TinymallGroupon groupon : grouponList.getList()) {
            try {
                Map<String, Object> recordData = new HashMap<>();
                List<TinymallGroupon> subGrouponList = grouponService.queryJoinRecord(groupon.getId());
                TinymallGrouponRules rules = rulesService.findById(groupon.getRulesId());
                TinymallGoods goods = goodsService.selectByPk(rules.getGoodsId());

                recordData.put("groupon", groupon);
                recordData.put("subGroupons", subGrouponList);
                recordData.put("rules", rules);
                recordData.put("goods", goods);

                groupons.add(recordData);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

        return grouponList;
    }

    @GetMapping("/list")
    public Object list(PageQO pageQO) {
        PageVO<TinymallGrouponRules> rulesList = rulesService.selectPage(pageQO);
        return rulesList;
    }

    @PostMapping("/update")
    public Object update(@RequestBody TinymallGrouponRules grouponRules) {
        TinymallGrouponRules rules = rulesService.findById(grouponRules.getId());
        AssertUtils.isFalse(rules == null,"参数不能为空");
        AssertUtils.isTrue(rules.getStatus().equals(GrouponConstant.RULE_STATUS_ON), "团购已经下线");

        Integer goodsId = grouponRules.getGoodsId();
        TinymallGoods goods = goodsService.selectByPk(goodsId);
        AssertUtils.isFalse(goods == null,"商品信息为空");

        grouponRules.setGoodsName(goods.getName());
        grouponRules.setPicUrl(goods.getPicUrl());

        AssertUtils.isFalse(rulesService.updateByPk(grouponRules.getId(),grouponRules) == 0,"需要更新的数据为空");
        return CommonResult.success();
    }

    @PostMapping("/create")
    public Object create(@RequestBody TinymallGrouponRules grouponRules) {
        Integer goodsId = grouponRules.getGoodsId();
        TinymallGoods goods = goodsService.selectByPk(goodsId);
        AssertUtils.isFalse(goods == null, "团购商品不存在");
        AssertUtils.isFalse(rulesService.countByGoodsId(goodsId) > 0, "团购商品已经存在");

        grouponRules.setGoodsName(goods.getName());
        grouponRules.setPicUrl(goods.getPicUrl());
        grouponRules.setStatus(GrouponConstant.RULE_STATUS_ON);
        rulesService.insert(grouponRules);

        /*LocalDateTime now = LocalDateTime.now();
        LocalDateTime expire = grouponRules.getExpireTime();
        long delay = ChronoUnit.MILLIS.between(now, expire);
        // 团购过期任务
        taskService.addTask(new GrouponRuleExpiredTask(grouponRules.getId(), delay));*/

        return grouponRules;
    }

    @PostMapping("/delete")
    public Object delete(@RequestBody TinymallGrouponRules grouponRules) {
        Integer id = grouponRules.getId();
        AssertUtils.isFalse(id == null,"团购id不能为空");

        rulesService.deleteByPk(id);
        return CommonResult.success();
    }
}

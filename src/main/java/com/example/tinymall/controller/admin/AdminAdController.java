package com.example.tinymall.controller.admin;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.common.result.CommonResult;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.core.validator.Order;
import com.example.tinymall.core.validator.Sort;
import com.example.tinymall.entity.TinymallAd;
import com.example.tinymall.model.po.Ad;
import com.example.tinymall.model.qo.AdQO;
import com.example.tinymall.service.TinymallAdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName AdminAdController
 * @Description
 * @Author jzf
 * @Date 2020-4-9 22:02
 */
@RestController
@ResponseResult
@Api(description = "首页广告图片")
@RequestMapping("/admin/ad")
public class AdminAdController {
    @Autowired
    private TinymallAdService adService;

    @GetMapping("/list")
    @ApiOperation(value = "查询广告图片列表", notes="查询广告图片列表")
    public Object list(PageQO page,AdQO condition) {
        page.setCondition(condition);
        return adService.selectPage(page);
    }

    @PostMapping("/create")
    @ApiOperation(value = "新增广告图片", notes="新增广告图片")
    @ApiImplicitParam(name = "ad", value = "用户登录信息", paramType = "TinymallAd", required = true, dataType = "TinymallAd")
    public Object create(@RequestBody Ad ad) {
        validate(ad);
        adService.insert(ad);
        return ad;
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改广告图片", notes="修改广告图片")
    public Object update(@RequestBody Ad ad) {
        validate(ad);
        adService.updateByPkSelective(ad.getId(),ad);
        return ad;
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除广告图片", notes="删除广告图片")
    public Object delete(@RequestBody TinymallAd ad) {
        Integer id = ad.getId();
        AssertUtils.isFalse(id == null,"id不能为空");
        adService.deleteByPk(id);
        return CommonResult.success();
    }

    private void validate(Ad ad) {
        String name = ad.getName();
        AssertUtils.isFalse(StringUtils.isEmpty(name),"名字不能为空");
        String content = ad.getContent();
        AssertUtils.isFalse(StringUtils.isEmpty(content),"内容不能为空");
    }
}

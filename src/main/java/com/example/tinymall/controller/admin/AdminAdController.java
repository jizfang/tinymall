package com.example.tinymall.controller.admin;

import com.example.tinymall.common.annotation.LoginAuth;
import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.core.util.ResponseUtil;
import com.example.tinymall.core.validator.Order;
import com.example.tinymall.core.validator.Sort;
import com.example.tinymall.domain.TinymallAd;
import com.example.tinymall.service.TinymallAdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Object list(String name, String content,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        PageVO<TinymallAd> adList = adService.querySelective(name, content, page, limit, sort, order);
        return adList;
    }

    @PostMapping("/create")
    @ApiOperation(value = "新增广告图片", notes="新增广告图片")
    @ApiImplicitParam(name = "ad", value = "用户登录信息", paramType = "TinymallAd", required = true, dataType = "TinymallAd")
    public Object create(@RequestBody TinymallAd ad) {
        Object error = validate(ad);
        if (error != null) {
            return error;
        }
        adService.add(ad);
        return ResponseUtil.ok(ad);
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改广告图片", notes="修改广告图片")
    public Object update(@RequestBody TinymallAd ad) {
        Object error = validate(ad);
        if (error != null) {
            return error;
        }
        if (adService.updateById(ad) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        return ResponseUtil.ok(ad);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除广告图片", notes="删除广告图片")
    public Object delete(@RequestBody TinymallAd ad) {
        Integer id = ad.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        adService.deleteById(id);
        return ResponseUtil.ok();
    }

    private Object validate(TinymallAd ad) {
        String name = ad.getName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        String content = ad.getContent();
        if (StringUtils.isEmpty(content)) {
            return ResponseUtil.badArgument();
        }
        return null;
    }
}

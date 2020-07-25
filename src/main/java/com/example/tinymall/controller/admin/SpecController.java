package com.example.tinymall.controller.admin;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.result.CommonResult;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.entity.Spec;
import com.example.tinymall.entity.TinymallTemplate;
import com.example.tinymall.model.po.Ad;
import com.example.tinymall.model.qo.AdQO;
import com.example.tinymall.service.SpecService;
import com.example.tinymall.service.TinymallTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 规格表Controller
 *
 * @author fang
 * @email jizhongfang@gmail.com
 * @date 2020-07-23 10:12:58
 */
@RestController
@ResponseResult
@Api(tags = "规格表Controller")
@RequestMapping("admin/spec")
public class SpecController {
    @Autowired
    private SpecService specService;
    @Autowired
    private TinymallTemplateService tinymallTemplateService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询规格表列表", notes="查询规格表列表")
    public Object list(PageQO page,Spec condition) {
        page.setCondition(condition);
        return specService.selectPage(page);
    }

    /**
     * 获取详情
     */
    @GetMapping("/read")
    @ApiOperation(value = "获取详情", notes="获取详情")
    public Spec detail(Integer id) {
        return specService.selectByPk(id);
    }

    /**
     * 保存
     */
    @PostMapping("/create")
    @ApiOperation(value = "新增规格表", notes="新增规格表")
    @ApiImplicitParam(name = "Spec", value = "规格表", paramType = "Spec", required = true, dataType = "Spec")
    public Object create(@Validated @RequestBody Spec spec) {
        specService.insert(spec);
        TinymallTemplate template = tinymallTemplateService.selectByPk(spec.getTemplateId());
        template.setSpecNum(template.getSpecNum() + 1);
        tinymallTemplateService.updateByPkSelective(template.getId(),template);
        return spec;
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改规格表", notes="修改规格表")
    public Object update(@Validated @RequestBody Spec spec) {
        specService.updateByPkSelective(spec.getId(),spec);
        return spec;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除规格表", notes="删除规格表")
    public Object delete(@RequestBody Spec spec) {
        Integer id = spec.getId();
        AssertUtils.isFalse(id == null,"id不能为空");
        specService.deleteByPk(id);
        TinymallTemplate template = tinymallTemplateService.selectByPk(spec.getTemplateId());
        template.setSpecNum(template.getSpecNum() - 1);
        tinymallTemplateService.updateByPkSelective(template.getId(),template);
        return CommonResult.success();
    }
}

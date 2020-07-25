package com.example.tinymall.controller.admin;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.result.CommonResult;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.entity.Spec;
import com.example.tinymall.entity.TinymallTemplate;
import com.example.tinymall.service.TinymallTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName TemplateController
 * @Description
 * @Author jzf
 * @Date 2020-7-22 18:31
 */
@RestController
@ResponseResult
@Api(tags = "模板表Controller")
@RequestMapping("admin/template")
public class TemplateController {
    @Autowired
    private TinymallTemplateService templateService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询模板表列表", notes="查询模板表列表")
    public Object list(PageQO page, TinymallTemplate condition) {
        page.setCondition(condition);
        return templateService.selectPage(page);
    }

    /**
     * 保存
     */
    @PostMapping("/create")
    @ApiOperation(value = "新增模板表", notes="新增模板表")
    @ApiImplicitParam(name = "Template", value = "模板表", paramType = "Template", required = true, dataType = "Template")
    public Object create(@Validated @RequestBody TinymallTemplate template) {
        template.setSpecNum(0);
        template.setParaNum(0);
        templateService.insert(template);
        return template;
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改模板表", notes="修改模板表")
    public Object update(@Validated @RequestBody TinymallTemplate template){
        templateService.updateByPkSelective(template.getId(),template);
        return template;
    }

     /**
      * 删除
      */
     @DeleteMapping("/delete")
     @ApiOperation(value = "删除模板表", notes="删除模板表")
     public Object delete(@RequestBody TinymallTemplate template) {
        Integer id = template.getId();
        AssertUtils.isFalse(id == null,"id不能为空");
        templateService.deleteByPk(id);
        return CommonResult.success();
     }

    /**
     * 获取所有的模板
     */
    @GetMapping("/getAll")
    @ApiOperation(value = "获取所有的模板", notes="获取所有的模板")
    public List<TinymallTemplate> getAll() {
        return templateService.selectAll();
    }
}

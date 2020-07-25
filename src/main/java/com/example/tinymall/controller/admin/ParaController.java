package com.example.tinymall.controller.admin;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.result.CommonResult;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.entity.Para;
import com.example.tinymall.entity.Spec;
import com.example.tinymall.entity.TinymallTemplate;
import com.example.tinymall.model.po.Ad;
import com.example.tinymall.model.qo.AdQO;
import com.example.tinymall.service.ParaService;
import com.example.tinymall.service.TinymallTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 参数表Controller
 *
 * @author fang
 * @email jizhongfang@gmail.com
 * @date 2020-07-23 10:12:58
 */
@RestController
@ResponseResult
@Api(tags = "参数表Controller")
@RequestMapping("admin/para")
public class ParaController {
    @Autowired
    private ParaService paraService;
    @Autowired
    private TinymallTemplateService tinymallTemplateService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询参数表列表", notes="查询参数表列表")
    public Object list(PageQO page,Para condition) {
        page.setCondition(condition);
        return paraService.selectPage(page);
    }

    /**
     * 获取详情
     */
    @GetMapping("/read")
    @ApiOperation(value = "获取详情", notes="获取详情")
    public Para detail(Integer id) {
        return paraService.selectByPk(id);
    }

    /**
     * 保存
     */
    @PostMapping("/create")
    @ApiOperation(value = "新增参数表", notes="新增参数表")
    @ApiImplicitParam(name = "Para", value = "参数表", paramType = "Para", required = true, dataType = "Para")
    public Object create(@Validated @RequestBody Para para) {
        paraService.insert(para);
        TinymallTemplate template = tinymallTemplateService.selectByPk(para.getTemplateId());
        template.setParaNum(template.getParaNum() + 1);
        tinymallTemplateService.updateByPkSelective(template.getId(),template);
        return para;
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改参数表", notes="修改参数表")
    public Object update(@Validated @RequestBody Para para) {
        paraService.updateByPkSelective(para.getId(),para);
        return para;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除参数表", notes="删除参数表")
    public Object delete(@RequestBody Para para) {
        Integer id = para.getId();
        AssertUtils.isFalse(id == null,"id不能为空");
        paraService.deleteByPk(id);
        TinymallTemplate template = tinymallTemplateService.selectByPk(para.getTemplateId());
        template.setParaNum(template.getParaNum() - 1);
        tinymallTemplateService.updateByPkSelective(template.getId(),template);
        return CommonResult.success();
    }
}

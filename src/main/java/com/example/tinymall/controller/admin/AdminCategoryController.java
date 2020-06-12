package com.example.tinymall.controller.admin;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.entity.TinymallCategory;
import com.example.tinymall.model.vo.CategoryVo;
import com.example.tinymall.service.TinymallCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminCategoryController
 * @Description 商品分类管理
 * @Author jzf
 * @Date 2020-5-12 16:05
 */
@ResponseResult
@RestController
@RequestMapping("/admin/category")
@Validated
public class AdminCategoryController {
    @Autowired
    private TinymallCategoryService categoryService;

    @GetMapping("/list")
    public Object list() {
        List<CategoryVo> categoryVoList = new ArrayList<>();

        List<TinymallCategory> categoryList = categoryService.queryByParentId(0);
        for (TinymallCategory category : categoryList) {
            CategoryVo categoryVO = new CategoryVo();
            categoryVO.setId(category.getId());
            categoryVO.setDesc(category.getDesc());
            categoryVO.setIconUrl(category.getIconUrl());
            categoryVO.setPicUrl(category.getPicUrl());
            categoryVO.setKeywords(category.getKeywords());
            categoryVO.setName(category.getName());
            categoryVO.setLevel(category.getLevel());

            List<CategoryVo> children = new ArrayList<>();
            List<TinymallCategory> subCategoryList = categoryService.queryByParentId(category.getId());
            for (TinymallCategory subCategory : subCategoryList) {
                CategoryVo subCategoryVo = new CategoryVo();
                subCategoryVo.setId(subCategory.getId());
                subCategoryVo.setDesc(subCategory.getDesc());
                subCategoryVo.setIconUrl(subCategory.getIconUrl());
                subCategoryVo.setPicUrl(subCategory.getPicUrl());
                subCategoryVo.setKeywords(subCategory.getKeywords());
                subCategoryVo.setName(subCategory.getName());
                subCategoryVo.setLevel(subCategory.getLevel());

                children.add(subCategoryVo);
            }

            categoryVO.setChildren(children);
            categoryVoList.add(categoryVO);
        }

        return categoryVoList;
    }

    @PostMapping("/create")
    public Object create(@RequestBody TinymallCategory category) {
        categoryService.insert(category);
        return category;
    }

    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        TinymallCategory category = categoryService.selectByPk(id);
        return category;
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@RequestBody TinymallCategory category) {
        AssertUtils.isFalse(categoryService.updateByPk(category.getId(),category) == 0,"需要更新的类目不存在");
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@RequestBody TinymallCategory category) {
        Integer id = category.getId();
        AssertUtils.isFalse(id == null,"类目不存在");
        categoryService.deleteByPk(id);
    }

    @GetMapping("/l1")
    public Object catL1() {
        // 所有一级分类目录
        List<TinymallCategory> l1CatList = categoryService.queryChannel();
        List<Map<String, Object>> data = new ArrayList<>(l1CatList.size());
        for (TinymallCategory category : l1CatList) {
            Map<String, Object> d = new HashMap<>(2);
            d.put("value", category.getId());
            d.put("label", category.getName());
            data.add(d);
        }
        return data;
    }
}

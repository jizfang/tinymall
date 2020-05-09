package com.example.tinymall.controller.wx;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.entity.TinymallCategory;
import com.example.tinymall.service.TinymallCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName WxCatalogController
 * @Description
 * @Author jzf
 * @Date 2020-4-11 17:24
 */
@ResponseResult
@RestController
@RequestMapping("/wx/catalog")
public class WxCatalogController {
    @Autowired
    private TinymallCategoryService categoryService;

    /**
     * 分类详情
     *
     * @param id   分类类目ID。
     *             如果分类类目ID是空，则选择第一个分类类目。
     *             需要注意，这里分类类目是一级类目
     * @return 分类详情
     */
    @GetMapping("index")
    public Object index(Integer id) {

        // 所有一级分类目录
        List<TinymallCategory> l1CatList = categoryService.queryChannel();

        // 当前一级分类目录
        TinymallCategory currentCategory = null;
        if (id != null) {
            currentCategory = categoryService.selectByPk(id);
        } else {
            if (l1CatList.size() > 0) {
                currentCategory = l1CatList.get(0);
            }
        }

        // 当前一级分类目录对应的二级分类目录
        List<TinymallCategory> currentSubCategory = null;
        if (null != currentCategory) {
            currentSubCategory = categoryService.queryByPid(currentCategory.getId());
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("categoryList", l1CatList);
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategory);
        return data;
    }

    /**
     * 当前分类栏目
     *
     * @param id 分类类目ID
     * @return 当前分类栏目
     */
    @GetMapping("current")
    public Object current(@NotNull Integer id) {
        // 当前分类
        TinymallCategory currentCategory = categoryService.selectByPk(id);
        /*if(currentCategory == null){
            return ResponseUtil.badArgumentValue();
        }*/
        List<TinymallCategory> currentSubCategory = categoryService.queryByPid(currentCategory.getId());

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategory);
        return data;
    }
}

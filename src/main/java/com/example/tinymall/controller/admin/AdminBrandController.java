package com.example.tinymall.controller.admin;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.core.utils.BeanUtil;
import com.example.tinymall.entity.TinymallBrand;
import com.example.tinymall.model.qo.BrandQO;
import com.example.tinymall.service.TinymallBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @ClassName AdminBrandController
 * @Description 品牌管理
 * @Author jzf
 * @Date 2020-5-12 17:36
 */
@ResponseResult
@RestController
@RequestMapping("/admin/brand")
@Validated
public class AdminBrandController {
    @Autowired
    private TinymallBrandService brandService;

    @GetMapping("/list")
    public Object list(PageQO page, BrandQO condition) {
        page.setCondition(condition);
        PageVO<TinymallBrand> brandList = brandService.selectPage(page);
        return brandList;
    }

    @PostMapping("/create")
    public Object create(@RequestBody TinymallBrand brand) {
        brandService.insert(brand);
        return brand;
    }

    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        TinymallBrand brand = brandService.selectByPk(id);
        return brand;
    }

    @PostMapping("/update")
    public Object update(@RequestBody TinymallBrand brand) {
        AssertUtils.isFalse(brandService.updateByPk(brand.getId(),brand) == 0,"需要更新的商品不存在");
        return brand;
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody TinymallBrand brand) {
        Integer id = brand.getId();
        AssertUtils.isFalse(id == null,"品牌id不能为空");
        brandService.deleteByPk(id);
    }
}

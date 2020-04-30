package com.example.tinymall.controller.admin;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.core.validator.Order;
import com.example.tinymall.core.validator.Sort;
import com.example.tinymall.domain.TinymallGoods;
import com.example.tinymall.domain.dto.GoodsDTO;
import com.example.tinymall.service.TinymallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @ClassName AdminGoodsController
 * @Description 商品管理
 * @Author jzf
 * @Date 2020-4-30 15:15
 */
@RestController
@RequestMapping("/admin/goods")
@ResponseResult
public class AdminGoodsController {
    @Autowired
    private TinymallGoodsService goodsService;

    @GetMapping("/list")
    public PageVO<TinymallGoods> list(Integer goodsId, String goodsSn, String name,
                                      @RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer limit,
                                      @Sort @RequestParam(defaultValue = "add_time") String sort,
                                      @Order @RequestParam(defaultValue = "desc") String order) {
        return goodsService.list(goodsId, goodsSn, name, page, limit, sort, order);
    }

    @PostMapping("/update")
    public Object update(@RequestBody GoodsDTO goodsAllinone) {
        return goodsService.update(goodsAllinone);
    }

    @PostMapping("/delete")
    public Object delete(@RequestBody TinymallGoods goods) {
        return goodsService.delete(goods);
    }

    @PostMapping("/create")
    public Object create(@RequestBody GoodsDTO goodsAllinone) {
        return goodsService.create(goodsAllinone);
    }

    @GetMapping("/detail")
    public Object detail(@NotNull Integer id) {
        return goodsService.detail(id);
    }

    @GetMapping("/catAndBrand")
    public Object list2() {
        return goodsService.list2();
    }
}

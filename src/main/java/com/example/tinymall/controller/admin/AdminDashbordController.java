package com.example.tinymall.controller.admin;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.entity.TinymallGoods;
import com.example.tinymall.entity.TinymallGoodsProduct;
import com.example.tinymall.entity.TinymallOrder;
import com.example.tinymall.entity.TinymallUser;
import com.example.tinymall.service.TinymallGoodsProductService;
import com.example.tinymall.service.TinymallGoodsService;
import com.example.tinymall.service.TinymallOrderService;
import com.example.tinymall.service.TinymallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AdminDashbordController
 * @Description
 * @Author jzf
 * @Date 2020-5-14 16:59
 */
@ResponseResult
@RestController
@RequestMapping("/admin/dashboard")
@Validated
public class AdminDashbordController {
    @Autowired
    private TinymallUserService userService;
    @Autowired
    private TinymallGoodsService goodsService;
    @Autowired
    private TinymallGoodsProductService productService;
    @Autowired
    private TinymallOrderService orderService;

    @GetMapping("")
    public Object info() {
        int userTotal = userService.selectCount(new TinymallUser());
        int goodsTotal = goodsService.selectCount(new TinymallGoods());
        int productTotal = productService.selectCount(new TinymallGoodsProduct());
        int orderTotal = orderService.selectCount(new TinymallOrder());
        Map<String, Integer> data = new HashMap<>();
        data.put("userTotal", userTotal);
        data.put("goodsTotal", goodsTotal);
        data.put("productTotal", productTotal);
        data.put("orderTotal", orderTotal);

        return data;
    }
}

package com.example.tinymall.controller.admin;

import com.example.tinymall.common.annotation.LoginAuth;
import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.core.util.ResponseUtil;
import com.example.tinymall.core.validator.Order;
import com.example.tinymall.core.validator.Sort;
import com.example.tinymall.domain.TinymallAd;
import com.example.tinymall.service.TinymallAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName AdminAdController
 * @Description
 * @Author jzf
 * @Date 2020-4-9 22:02
 */
@RestController
@RequestMapping("/admin/ad")
@ResponseResult
public class AdminAdController {
    @Autowired
    private TinymallAdService adService;

    @GetMapping("/list")
    @LoginAuth
    public PageVO<TinymallAd> list(String name, String content,
                                   @RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer limit,
                                   @Sort @RequestParam(defaultValue = "add_time") String sort,
                                   @Order @RequestParam(defaultValue = "desc") String order) {
        PageVO<TinymallAd> adList = adService.querySelective(name, content, page, limit, sort, order);
        return adList;
    }
}

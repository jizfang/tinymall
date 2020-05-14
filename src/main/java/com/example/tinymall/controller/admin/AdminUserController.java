package com.example.tinymall.controller.admin;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.entity.TinymallAddress;
import com.example.tinymall.entity.TinymallUser;
import com.example.tinymall.model.qo.RegionQO;
import com.example.tinymall.model.qo.UserQO;
import com.example.tinymall.service.TinymallAddressService;
import com.example.tinymall.service.TinymallRegionService;
import com.example.tinymall.service.TinymallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AdminUserController
 * @Description 用户管理
 * @Author jzf
 * @Date 2020-5-14 14:52
 */
@ResponseResult
@RestController
@RequestMapping("/admin")
@Validated
public class AdminUserController {
    @Autowired
    private TinymallUserService userService;
    @Autowired
    private TinymallAddressService addressService;
    @Autowired
    private TinymallRegionService regionService;
    
    @GetMapping("/user/list")
    public Object list(PageQO pageQO, UserQO userQO) {
        pageQO.setCondition(userQO);
        PageVO<TinymallUser> userList = userService.selectPage(pageQO);
        return userList;
    }
    
    @GetMapping("/address/list")
    public Object list(PageQO pageQO,RegionQO regionQO) {
        pageQO.setCondition(regionQO);
        PageVO<TinymallAddress> addressList = addressService.selectPage(pageQO);
        return addressList;
    }
}

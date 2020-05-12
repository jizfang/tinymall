package com.example.tinymall.controller.wx;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.entity.TinymallAddress;
import com.example.tinymall.model.bo.LoginUser;
import com.example.tinymall.service.TinymallAddressService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName WxAddressController
 * @Description
 * @Author jzf
 * @Date 2020-4-15 20:08
 */
@ResponseResult
@RestController
@RequestMapping("/wx/address")
public class WxAddressController {
    @Autowired
    private TinymallAddressService addressService;

    /**
     * 用户收货地址列表
     *
     * @return 收货地址列表
     */
    @GetMapping("list")
    public Object list() {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        AssertUtils.notNull(loginUser,"用户未登录");
        PageQO pageQO = new PageQO();
        TinymallAddress tinymallAddress = new TinymallAddress();
        tinymallAddress.setUserId(loginUser.getId());
        pageQO.setCondition(tinymallAddress);
        PageVO<TinymallAddress> addressList = addressService.selectPage(pageQO);
        return addressList;
    }

    /**
     * 添加或更新收货地址
     *
     * @param address 用户收货地址
     * @return 添加或更新操作结果
     */
    @PostMapping("save")
    public Object save(@RequestBody @Validated TinymallAddress address) {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        Integer userId = Integer.valueOf(loginUser.getId());

        if (address.getId() == null || address.getId().equals(0)) {
            if (address.getIsDefault()) {
                // 重置其他收货地址的默认选项
                addressService.resetDefault(userId);
            }

            address.setId(null);
            address.setUserId(userId);
            addressService.insert(address);
        } else {
            TinymallAddress litemallAddress = addressService.query(userId, address.getId());
            AssertUtils.isFalse(litemallAddress == null,"需要修改的地址信息不存在");

            if (address.getIsDefault()) {
                // 重置其他收货地址的默认选项
                addressService.resetDefault(userId);
            }

            address.setUserId(userId);
            addressService.updateByPk(address.getId(),address);
        }
        return address.getId();
    }
}

package com.example.tinymall.controller.wx;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.core.utils.RegexUtil;
import com.example.tinymall.entity.TinymallAddress;
import com.example.tinymall.model.bo.LoginUser;
import com.example.tinymall.service.TinymallAddressService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<TinymallAddress> addressList = addressService.queryByUid(loginUser.getId());
        return addressList;
    }

    /**
     * 添加或更新收货地址
     *
     * @param address 用户收货地址
     * @return 添加或更新操作结果
     */
    @PostMapping("save")
    public Object save(@RequestBody TinymallAddress address) {
        com.example.tinymall.model.bo.LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        Integer userId = Integer.valueOf(loginUser.getId());
        Object error = validate(address);
        if (error != null) {
            return error;
        }

        if (address.getId() == null || address.getId().equals(0)) {
            if (address.getIsDefault()) {
                // 重置其他收货地址的默认选项
                addressService.resetDefault(userId);
            }

            address.setId(null);
            address.setUserId(userId);
            addressService.add(address);
        } else {
            TinymallAddress litemallAddress = addressService.query(userId, address.getId());
            /*if (litemallAddress == null) {
                return ResponseUtil.badArgumentValue();
            }*/

            if (address.getIsDefault()) {
                // 重置其他收货地址的默认选项
                addressService.resetDefault(userId);
            }

            address.setUserId(userId);
            addressService.update(address);
        }
        return address.getId();
    }

    private Object validate(TinymallAddress address) {
        String name = address.getName();
        /*if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }*/

        // 测试收货手机号码是否正确
        String mobile = address.getTel();
        /*if (StringUtils.isEmpty(mobile)) {
            return ResponseUtil.badArgument();
        }
        if (!RegexUtil.isMobileExact(mobile)) {
            return ResponseUtil.badArgument();
        }

        String province = address.getProvince();
        if (StringUtils.isEmpty(province)) {
            return ResponseUtil.badArgument();
        }

        String city = address.getCity();
        if (StringUtils.isEmpty(city)) {
            return ResponseUtil.badArgument();
        }

        String county = address.getCounty();
        if (StringUtils.isEmpty(county)) {
            return ResponseUtil.badArgument();
        }


        String areaCode = address.getAreaCode();
        if (StringUtils.isEmpty(areaCode)) {
            return ResponseUtil.badArgument();
        }

        String detailedAddress = address.getAddressDetail();
        if (StringUtils.isEmpty(detailedAddress)) {
            return ResponseUtil.badArgument();
        }

        Boolean isDefault = address.getIsDefault();
        if (isDefault == null) {
            return ResponseUtil.badArgument();
        }*/
        return null;
    }
}

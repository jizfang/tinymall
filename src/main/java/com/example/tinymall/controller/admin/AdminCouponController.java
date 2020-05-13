package com.example.tinymall.controller.admin;

import com.example.tinymall.common.annotation.LoginAuth;
import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.core.constants.CouponConstant;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.entity.TinymallCoupon;
import com.example.tinymall.model.bo.LoginUser;
import com.example.tinymall.model.qo.CouponQO;
import com.example.tinymall.service.TinymallCouponService;
import com.example.tinymall.service.TinymallCouponUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @description: 优惠券
 * @author: fang
 * @create: 2020-05-13 23:24
 **/
@ResponseResult
@RestController
@RequestMapping("/admin/coupon")
@Validated
@LoginAuth
public class AdminCouponController {
    @Autowired
    private TinymallCouponService couponService;
    @Autowired
    private TinymallCouponUserService couponUserService;
    
    @GetMapping("/list")
    public Object list(PageQO pageQO, CouponQO couponQO) {
        pageQO.setCondition(couponQO);
        PageVO<TinymallCoupon> couponList = couponService.selectPage(pageQO);
        return couponList;
    }
    
    @GetMapping("/listuser")
    public Object listuser(PageQO pageQO, CouponQO couponQO) {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        Integer userId = loginUser.getId();
        couponQO.setUserId(userId);
        pageQO.setCondition(couponQO);
        PageVO<TinymallCoupon> couponList = couponService.selectPage(pageQO);
        return couponList;
    }
    
    @PostMapping("/create")
    public Object create(@RequestBody TinymallCoupon coupon) {

        // 如果是兑换码类型，则这里需要生存一个兑换码
        if (coupon.getType().equals(CouponConstant.TYPE_CODE)) {
            String code = couponService.generateCode();
            coupon.setCode(code);
        }

        couponService.insert(coupon);
        return coupon;
    }
    
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        TinymallCoupon coupon = couponService.selectByPk(id);
        return coupon;
    }

    @PostMapping("/update")
    public Object update(@RequestBody TinymallCoupon coupon) {
        AssertUtils.isFalse(couponService.updateByPk(coupon.getId(),coupon) == 0,"更新优惠券失败");
        return coupon;
    }
    
    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@RequestBody TinymallCoupon coupon) {
        couponService.deleteByPk(coupon.getId());
    }
}

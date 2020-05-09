package com.example.tinymall.controller.wx;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.core.validator.Order;
import com.example.tinymall.core.validator.Sort;
import com.example.tinymall.entity.TinymallCoupon;
import com.example.tinymall.entity.TinymallCouponUser;
import com.example.tinymall.model.bo.LoginUser;
import com.example.tinymall.model.vo.CouponVo;
import com.example.tinymall.service.TinymallCouponService;
import com.example.tinymall.service.TinymallCouponUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WxCouponController
 * @Description
 * @Author jzf
 * @Date 2020-4-15 19:57
 */
@ResponseResult
@RestController
@RequestMapping("/wx/coupon")
public class WxCouponController {
    @Autowired
    private TinymallCouponService couponService;
    @Autowired
    private TinymallCouponUserService couponUserService;
    /**
     * 个人优惠券列表
     *
     * @param status
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("mylist")
    public Object mylist(
                         Short status,
                         @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "10") Integer limit,
                         @Sort @RequestParam(defaultValue = "add_time") String sort,
                         @Order @RequestParam(defaultValue = "desc") String order) {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        AssertUtils.notNull(loginUser,"用户未登录");
        Integer userId = loginUser.getId();

        List<TinymallCouponUser> couponUserList = couponUserService.queryList(userId, null, status, page, limit, sort, order);
        List<CouponVo> couponVoList = change(couponUserList);
        return couponUserList;
    }

    private List<CouponVo> change(List<TinymallCouponUser> couponList) {
        List<CouponVo> couponVoList = new ArrayList<>(couponList.size());
        for(TinymallCouponUser couponUser : couponList){
            Integer couponId = couponUser.getCouponId();
            TinymallCoupon coupon = couponService.selectByPk(couponId);
            CouponVo couponVo = new CouponVo();
            couponVo.setId(couponUser.getId());
            couponVo.setCid(coupon.getId());
            couponVo.setName(coupon.getName());
            couponVo.setDesc(coupon.getDesc());
            couponVo.setTag(coupon.getTag());
            couponVo.setMin(coupon.getMin());
            couponVo.setDiscount(coupon.getDiscount());
            couponVo.setStartTime(couponUser.getStartTime());
            couponVo.setEndTime(couponUser.getEndTime());

            couponVoList.add(couponVo);
        }

        return couponVoList;
    }
}

package com.example.tinymall.controller.wx;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.core.validator.Order;
import com.example.tinymall.core.validator.Sort;
import com.example.tinymall.entity.TinymallCoupon;
import com.example.tinymall.entity.TinymallCouponUser;
import com.example.tinymall.model.bo.LoginUser;
import com.example.tinymall.model.vo.CouponVo;
import com.example.tinymall.model.vo.FootprintVO;
import com.example.tinymall.service.TinymallCouponService;
import com.example.tinymall.service.TinymallCouponUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
     * @param pageQO
     * @return
     */
    @GetMapping("mylist")
    public Object mylist(Short status, PageQO pageQO) {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        AssertUtils.notNull(loginUser,"用户未登录");
        Integer userId = loginUser.getId();
        TinymallCouponUser condition = new TinymallCouponUser();
        condition.setUserId(userId);
        condition.setStatus(status);

        pageQO.setOrderBy("cu.create_time desc");
        Page<CouponVo> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize(), pageQO.getOrderBy());
        couponUserService.queryList(condition);
        return PageVO.build(page);
    }
}

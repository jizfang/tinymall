package com.example.tinymall.service.impl;

import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.core.constants.CouponConstant;
import com.example.tinymall.entity.TinymallCoupon;
import com.example.tinymall.mapper.TinymallCouponMapper;
import com.example.tinymall.mapper.TinymallCouponUserMapper;
import com.example.tinymall.service.TinymallCouponService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TinymallCouponServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-11 10:19
 */
@Service
public class TinymallCouponServiceImpl implements TinymallCouponService {

    @Resource
    private TinymallCouponMapper couponMapper;
    @Resource
    private TinymallCouponUserMapper couponUserMapper;

    @Override
    public PageVO<TinymallCoupon> queryList(PageQO pageQO) {
        /*criteria.andTypeEqualTo(CouponConstant.TYPE_COMMON).andStatusEqualTo(CouponConstant.STATUS_NORMAL).andDeletedEqualTo(false);
        criteria.example().setOrderByClause(sort + " " + order);
        PageHelper.startPage(offset, limit);
        return couponMapper.selectByExampleSelective(criteria.example(), result);*/
        return null;
    }

    @Override
    public List<TinymallCoupon> queryRegister() {
        /*TinymallCouponExample example = new TinymallCouponExample();
        example.or().andTypeEqualTo(CouponConstant.TYPE_REGISTER).andStatusEqualTo(CouponConstant.STATUS_NORMAL).andDeletedEqualTo(false);
        return couponMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public TinymallCoupon findById(Integer couponId) {
        return couponMapper.selectByPrimaryKey(couponId);
    }
}

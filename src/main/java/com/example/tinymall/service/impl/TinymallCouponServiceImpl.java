package com.example.tinymall.service.impl;

import com.example.tinymall.core.constants.CouponConstant;
import com.example.tinymall.dao.TinymallCouponMapper;
import com.example.tinymall.dao.TinymallCouponUserMapper;
import com.example.tinymall.domain.TinymallCoupon;
import com.example.tinymall.domain.TinymallCouponExample;
import com.example.tinymall.service.TinymallCouponService;
import com.github.pagehelper.PageHelper;
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

    private TinymallCoupon.Column[] result = new TinymallCoupon.Column[]{TinymallCoupon.Column.id, TinymallCoupon.Column.name, TinymallCoupon.Column.desc,
            TinymallCoupon.Column.tag, TinymallCoupon.Column.days, TinymallCoupon.Column.startTime, TinymallCoupon.Column.endTime,
            TinymallCoupon.Column.discount, TinymallCoupon.Column.min};

    @Override
    public List queryList(int offset, int limit) {
        return queryList(offset, limit, "add_time", "desc");
    }

    @Override
    public List<TinymallCoupon> queryList(TinymallCouponExample.Criteria criteria, int offset, int limit, String sort, String order) {
        criteria.andTypeEqualTo(CouponConstant.TYPE_COMMON).andStatusEqualTo(CouponConstant.STATUS_NORMAL).andDeletedEqualTo(false);
        criteria.example().setOrderByClause(sort + " " + order);
        PageHelper.startPage(offset, limit);
        return couponMapper.selectByExampleSelective(criteria.example(), result);
    }

    @Override
    public List<TinymallCoupon> queryList(int offset, int limit, String sort, String order) {
        return queryList(TinymallCouponExample.newAndCreateCriteria(), offset, limit, sort, order);
    }

    @Override
    public List<TinymallCoupon> queryRegister() {
        TinymallCouponExample example = new TinymallCouponExample();
        example.or().andTypeEqualTo(CouponConstant.TYPE_REGISTER).andStatusEqualTo(CouponConstant.STATUS_NORMAL).andDeletedEqualTo(false);
        return couponMapper.selectByExample(example);
    }

    @Override
    public TinymallCoupon findById(Integer couponId) {
        return couponMapper.selectByPrimaryKey(couponId);
    }
}

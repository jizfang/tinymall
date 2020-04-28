package com.example.tinymall.service.impl;

import com.example.tinymall.core.constants.CouponUserConstant;
import com.example.tinymall.dao.TinymallCouponUserMapper;
import com.example.tinymall.domain.TinymallCouponUser;
import com.example.tinymall.domain.TinymallCouponUserExample;
import com.example.tinymall.service.TinymallCouponUserService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName TinymallCouponUserServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-14 20:16
 */
@Service
public class TinymallCouponUserServiceImpl implements TinymallCouponUserService {
    @Resource
    private TinymallCouponUserMapper couponUserMapper;

    @Override
    public Integer countCoupon(Integer couponId) {
        TinymallCouponUserExample example = new TinymallCouponUserExample();
        example.or().andCouponIdEqualTo(couponId).andDeletedEqualTo(false);
        return (int) couponUserMapper.countByExample(example);
    }

    @Override
    public Integer countUserAndCoupon(Integer userId, Integer couponId) {
        TinymallCouponUserExample example = new TinymallCouponUserExample();
        example.or().andUserIdEqualTo(userId).andCouponIdEqualTo(couponId).andDeletedEqualTo(false);
        return (int) couponUserMapper.countByExample(example);
    }

    @Override
    public void add(TinymallCouponUser couponUser) {
        couponUser.setAddTime(LocalDateTime.now());
        couponUser.setUpdateTime(LocalDateTime.now());
        couponUserMapper.insertSelective(couponUser);
    }

    @Override
    public List<TinymallCouponUser> queryList(Integer userId, Integer couponId, Short status, Integer page, Integer size, String sort, String order) {
        TinymallCouponUserExample example = new TinymallCouponUserExample();
        TinymallCouponUserExample.Criteria criteria = example.createCriteria();
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (couponId != null) {
            criteria.andCouponIdEqualTo(couponId);
        }
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        if (page != null) {
            PageHelper.startPage(page, size);
        }

        return couponUserMapper.selectByExample(example);
    }

    @Override
    public List<TinymallCouponUser> queryAll(Integer userId, Integer couponId) {
        return queryList(userId, couponId, CouponUserConstant.STATUS_USABLE, null, null, "add_time", "desc");
    }

    @Override
    public List<TinymallCouponUser> queryAll(Integer userId) {
        return queryList(userId, null, CouponUserConstant.STATUS_USABLE, null, null, "add_time", "desc");
    }

    @Override
    public TinymallCouponUser queryOne(Integer userId, Integer couponId) {
        List<TinymallCouponUser> couponUserList = queryList(userId, couponId, CouponUserConstant.STATUS_USABLE, 1, 1, "add_time", "desc");
        if (couponUserList.size() == 0) {
            return null;
        }
        return couponUserList.get(0);
    }

    @Override
    public TinymallCouponUser findById(Integer id) {
        return couponUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(TinymallCouponUser couponUser) {
        couponUser.setUpdateTime(LocalDateTime.now());
        return couponUserMapper.updateByPrimaryKeySelective(couponUser);
    }

    @Override
    public List<TinymallCouponUser> queryExpired() {
        TinymallCouponUserExample example = new TinymallCouponUserExample();
        example.or().andStatusEqualTo(CouponUserConstant.STATUS_USABLE).andEndTimeLessThan(LocalDateTime.now()).andDeletedEqualTo(false);
        return couponUserMapper.selectByExample(example);
    }
}

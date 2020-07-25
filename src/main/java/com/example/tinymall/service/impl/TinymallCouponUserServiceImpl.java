package com.example.tinymall.service.impl;

import com.example.tinymall.core.constants.CouponUserConstant;
import com.example.tinymall.entity.TinymallCouponUser;
import com.example.tinymall.mapper.TinymallCouponUserMapper;
import com.example.tinymall.model.vo.CouponVo;
import com.example.tinymall.service.TinymallCouponUserService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
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
@Primary
public class TinymallCouponUserServiceImpl implements TinymallCouponUserService {
    @Resource
    private TinymallCouponUserMapper couponUserMapper;

    @Override
    public Integer countCoupon(Integer couponId) {
        /*TinymallCouponUserExample example = new TinymallCouponUserExample();
        example.or().andCouponIdEqualTo(couponId).andDeletedEqualTo(false);
        return (int) couponUserMapper.countByExample(example);*/
        return 0;
    }

    @Override
    public Integer countUserAndCoupon(Integer userId, Integer couponId) {
        /*TinymallCouponUserExample example = new TinymallCouponUserExample();
        example.or().andUserIdEqualTo(userId).andCouponIdEqualTo(couponId).andDeletedEqualTo(false);
        return (int) couponUserMapper.countByExample(example);*/
        return 0;
    }

    @Override
    public void add(TinymallCouponUser couponUser) {
        couponUser.setAddTime(LocalDateTime.now());
        couponUser.setUpdateTime(LocalDateTime.now());
        couponUserMapper.insertSelective(couponUser);
    }

    @Override
    public List<CouponVo> queryList(TinymallCouponUser condition) {
        return couponUserMapper.queryList(condition);
    }

    @Override
    public List<CouponVo> queryAll(Integer userId) {
        TinymallCouponUser condition = new TinymallCouponUser();
        condition.setUserId(userId);
        condition.setStatus(CouponUserConstant.STATUS_USABLE);
        return queryList(condition);
    }

    @Override
    public CouponVo queryOne(Integer userId, Integer couponId) {
        TinymallCouponUser condition = new TinymallCouponUser();
        condition.setUserId(userId);
        condition.setStatus(CouponUserConstant.STATUS_USABLE);
        condition.setCouponId(couponId);
        List<CouponVo> couponUserList = queryList(condition);
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
        /*TinymallCouponUserExample example = new TinymallCouponUserExample();
        example.or().andStatusEqualTo(CouponUserConstant.STATUS_USABLE).andEndTimeLessThan(LocalDateTime.now()).andDeletedEqualTo(false);
        return couponUserMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }
}

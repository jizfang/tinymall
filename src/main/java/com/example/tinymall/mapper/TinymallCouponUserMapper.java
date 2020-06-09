package com.example.tinymall.mapper;

import com.example.tinymall.common.minemappers.MyMapper;
import com.example.tinymall.entity.TinymallCouponUser;
import com.example.tinymall.model.vo.CouponVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TinymallCouponUserMapper extends MyMapper<TinymallCouponUser> {
    List<CouponVo> queryList(@Param("condition") TinymallCouponUser condition);
}
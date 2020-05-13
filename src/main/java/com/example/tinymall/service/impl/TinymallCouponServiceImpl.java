package com.example.tinymall.service.impl;

import com.example.tinymall.common.Exceptions.BusinessException;
import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
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
import java.util.Random;

/**
 * @ClassName TinymallCouponServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-11 10:19
 */
@Service
public class TinymallCouponServiceImpl extends BaseMySqlServiceImpl<TinymallCoupon,Integer> implements TinymallCouponService {

    @Resource
    private TinymallCouponMapper couponMapper;
    @Resource
    private TinymallCouponUserMapper couponUserMapper;

    @Override
    public List<TinymallCoupon> queryRegister() {
        /*TinymallCouponExample example = new TinymallCouponExample();
        example.or().andTypeEqualTo(CouponConstant.TYPE_REGISTER).andStatusEqualTo(CouponConstant.STATUS_NORMAL).andDeletedEqualTo(false);
        return couponMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    private String getRandomNum(Integer num) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        base += "0123456789";

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成优惠码
     *
     * @return 可使用优惠码
     */
    @Override
    public String generateCode() {
        String code = getRandomNum(8);
        while(findByCode(code) != null){
            code = getRandomNum(8);
        }
        return code;
    }

    @Override
    public TinymallCoupon findByCode(String code) {
        TinymallCoupon coupon = new TinymallCoupon();
        coupon.setCode(code);
        coupon.setType(CouponConstant.TYPE_CODE);
        coupon.setStatus(CouponConstant.STATUS_NORMAL);
        coupon.setDeleted(0);

        List<TinymallCoupon> couponList =  couponMapper.selectByExample(coupon);
        if(couponList.size() > 1){
            throw new BusinessException("");
        }
        else if(couponList.size() == 0){
            return null;
        }
        else {
            return couponList.get(0);
        }
    }
}

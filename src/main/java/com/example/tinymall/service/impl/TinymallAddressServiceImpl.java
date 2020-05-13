package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.entity.TinymallAddress;
import com.example.tinymall.mapper.TinymallAddressMapper;
import com.example.tinymall.service.TinymallAddressService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName TinymallAddressServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-15 20:13
 */
@Service
public class TinymallAddressServiceImpl extends BaseMySqlServiceImpl<TinymallAddress,Integer> implements TinymallAddressService {
    @Resource
    private TinymallAddressMapper addressMapper;

    @Override
    public List<TinymallAddress> queryByUid(Integer uid) {
        TinymallAddress address = new TinymallAddress();
        address.setUserId(uid);
        address.setDeleted(0);
        return addressMapper.select(address);
    }

    @Override
    public TinymallAddress query(Integer userId, Integer id) {
        TinymallAddress address = new TinymallAddress();
        address.setId(id);
        address.setUserId(userId);
        address.setDeleted(0);
        return addressMapper.selectOne(address);
    }

    @Override
    public TinymallAddress findDefault(Integer userId) {
        TinymallAddress address = new TinymallAddress();
        address.setUserId(userId);
        address.setDeleted(0);
        address.setIsDefault(true);
        return addressMapper.selectOne(address);
    }

    @Override
    public void resetDefault(Integer userId) {
        /*TinymallAddress address = new TinymallAddress();
        address.setIsDefault(false);
        address.setUpdateTime(LocalDateTime.now());
        TinymallAddressExample example = new TinymallAddressExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        addressMapper.updateByExampleSelective(address, example);*/
    }
}

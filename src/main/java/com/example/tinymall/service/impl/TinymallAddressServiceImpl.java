package com.example.tinymall.service.impl;

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
public class TinymallAddressServiceImpl implements TinymallAddressService {
    @Resource
    private TinymallAddressMapper addressMapper;

    @Override
    public List<TinymallAddress> queryByUid(Integer uid) {
        /*TinymallAddressExample example = new TinymallAddressExample();
        example.or().andUserIdEqualTo(uid).andDeletedEqualTo(false);
        return addressMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public TinymallAddress query(Integer userId, Integer id) {
        /*TinymallAddressExample example = new TinymallAddressExample();
        example.or().andIdEqualTo(id).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return addressMapper.selectOneByExample(example);*/
        return null;
    }

    @Override
    public int add(TinymallAddress address) {
        address.setAddTime(LocalDateTime.now());
        address.setUpdateTime(LocalDateTime.now());
        return addressMapper.insertSelective(address);
    }

    @Override
    public int update(TinymallAddress address) {
        address.setUpdateTime(LocalDateTime.now());
        return addressMapper.updateByPrimaryKeySelective(address);
    }

    @Override
    public void delete(Integer id) {
        addressMapper.deleteByIds(String.valueOf(id));
    }

    @Override
    public TinymallAddress findDefault(Integer userId) {
        /*TinymallAddressExample example = new TinymallAddressExample();
        example.or().andUserIdEqualTo(userId).andIsDefaultEqualTo(true).andDeletedEqualTo(false);
        return addressMapper.selectOneByExample(example);*/
        return null;
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

    @Override
    public List<TinymallAddress> querySelective(Integer userId, String name, Integer page, Integer limit, String sort, String order) {
        /*TinymallAddressExample example = new TinymallAddressExample();
        TinymallAddressExample.Criteria criteria = example.createCriteria();

        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return addressMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }
}

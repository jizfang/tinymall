package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.entity.TinymallUser;
import com.example.tinymall.mapper.TinymallUserMapper;
import com.example.tinymall.model.vo.UserVo;
import com.example.tinymall.service.TinymallUserService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName TinymallUserServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-14 20:06
 */
@Service
public class TinymallUserServiceImpl extends BaseMySqlServiceImpl<TinymallUser,Integer> implements TinymallUserService {

    @Resource
    private TinymallUserMapper userMapper;

    @Override
    public TinymallUser findById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public UserVo findUserVoById(Integer userId) {
        TinymallUser user = findById(userId);
        UserVo userVo = new UserVo();
        userVo.setNickname(user.getNickname());
        userVo.setAvatar(user.getAvatar());
        return userVo;
    }

    @Override
    public TinymallUser queryByOid(String openId) {
        /*TinymallUserExample example = new TinymallUserExample();
        example.or().andWeixinOpenidEqualTo(openId).andDeletedEqualTo(false);
        return userMapper.selectOneByExample(example);*/
        return null;
    }

    @Override
    public List<TinymallUser> querySelective(String username, String mobile, Integer page, Integer size, String sort, String order) {
        /*TinymallUserExample example = new TinymallUserExample();
        TinymallUserExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        if (!StringUtils.isEmpty(mobile)) {
            criteria.andMobileEqualTo(mobile);
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return userMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public int count() {
        /*TinymallUserExample example = new TinymallUserExample();
        example.or().andDeletedEqualTo(false);

        return (int) userMapper.countByExample(example);*/
        return 0;
    }

    @Override
    public List<TinymallUser> queryByUsername(String username) {
        /*TinymallUserExample example = new TinymallUserExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return userMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public boolean checkByUsername(String username) {
        /*TinymallUserExample example = new TinymallUserExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return userMapper.countByExample(example) != 0;*/
        return false;
    }

    @Override
    public List<TinymallUser> queryByMobile(String mobile) {
        /*TinymallUserExample example = new TinymallUserExample();
        example.or().andMobileEqualTo(mobile).andDeletedEqualTo(false);
        return userMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public List<TinymallUser> queryByOpenid(String openid) {
        /*TinymallUserExample example = new TinymallUserExample();
        example.or().andWeixinOpenidEqualTo(openid).andDeletedEqualTo(false);
        return userMapper.selectByExample(example);*/
        return Lists.newArrayList();
    }

    @Override
    public void deleteById(Integer id) {
        //userMapper.logicalDeleteByPrimaryKey(id);
    }
}

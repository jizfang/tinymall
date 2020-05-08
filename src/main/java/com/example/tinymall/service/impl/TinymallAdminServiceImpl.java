package com.example.tinymall.service.impl;

import com.example.tinymall.entity.TinymallAdmin;
import com.example.tinymall.mapper.TinymallAdminMapper;
import com.example.tinymall.service.TinymallAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName TinymallAdminServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-28 15:11
 */
@Service
public class TinymallAdminServiceImpl implements TinymallAdminService {

    @Autowired
    private TinymallAdminMapper adminMapper;

    @Override
    public List<TinymallAdmin> queryByUsername(String username) {
        Example example = new Example(TinymallAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        return adminMapper.selectByExample(example);
    }

    @Override
    public int updateById(TinymallAdmin user) {
        user.setUpdateTime(LocalDateTime.now());
        return adminMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public TinymallAdmin getByUserId(int userId) {
        return adminMapper.selectByPrimaryKey(userId);
    }
}

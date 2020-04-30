package com.example.tinymall.service.impl;

import com.example.tinymall.dao.TinymallAdminMapper;
import com.example.tinymall.domain.TinymallAdmin;
import com.example.tinymall.domain.TinymallAdminExample;
import com.example.tinymall.service.TinymallAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        TinymallAdminExample example = new TinymallAdminExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
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

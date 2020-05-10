package com.example.tinymall.service;

import com.example.tinymall.common.mineservice.BaseService;
import com.example.tinymall.entity.TinymallUser;
import com.example.tinymall.model.vo.UserVo;

import java.util.List;

/**
 * @ClassName TinymallUserService
 * @Description
 * @Author jzf
 * @Date 2020-4-14 19:58
 */
public interface TinymallUserService extends BaseService<TinymallUser,Integer> {
    TinymallUser findById(Integer userId);

    UserVo findUserVoById(Integer userId);

    TinymallUser queryByOid(String openId);

    List<TinymallUser> querySelective(String username, String mobile, Integer page, Integer size, String sort, String order);

    int count();

    List<TinymallUser> queryByUsername(String username);

    boolean checkByUsername(String username);

    List<TinymallUser> queryByMobile(String mobile);

    List<TinymallUser> queryByOpenid(String openid);

    void deleteById(Integer id);
}

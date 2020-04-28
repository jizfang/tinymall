package com.example.tinymall.service;

import com.example.tinymall.domain.TinymallAdmin;

import java.util.List;

/**
 * @ClassName TinymallAdminService
 * @Description
 * @Author jzf
 * @Date 2020-4-28 15:11
 */
public interface TinymallAdminService {
    List<TinymallAdmin> queryByUsername(String username);

    int updateById(TinymallAdmin user);
}

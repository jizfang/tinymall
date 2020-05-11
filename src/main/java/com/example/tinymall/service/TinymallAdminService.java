package com.example.tinymall.service;

import com.example.tinymall.common.mineservice.BaseService;
import com.example.tinymall.entity.TinymallAdmin;

import java.util.List;

/**
 * @ClassName TinymallAdminService
 * @Description
 * @Author jzf
 * @Date 2020-4-28 15:11
 */
public interface TinymallAdminService extends BaseService<TinymallAdmin,Integer> {
    List<TinymallAdmin> queryByUsername(String username);
}

package com.example.tinymall.service;

import com.example.tinymall.common.mineservice.BaseService;
import com.example.tinymall.entity.TinymallSystem;

import java.util.Map;

/**
 * @ClassName LitemallSystemConfigService
 * @Description
 * @Author jzf
 * @Date 2020-4-23 11:43
 */
public interface TinymallSystemConfigService extends BaseService<TinymallSystem,Integer> {
    Map<String, String> queryAll();
    Map<String, String> listMail();
    Map<String, String> listWx();
    Map<String, String> listOrder();
    Map<String, String> listExpress();
    void updateConfig(Map<String, String> data);
}

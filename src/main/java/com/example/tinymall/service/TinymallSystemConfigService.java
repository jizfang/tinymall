package com.example.tinymall.service;

import java.util.Map;

/**
 * @ClassName LitemallSystemConfigService
 * @Description
 * @Author jzf
 * @Date 2020-4-23 11:43
 */
public interface TinymallSystemConfigService {
    Map<String, String> queryAll();
    Map<String, String> listMail();
    Map<String, String> listWx();
    Map<String, String> listOrder();
    Map<String, String> listExpress();
    void updateConfig(Map<String, String> data);
    void addConfig(String key, String value);
}

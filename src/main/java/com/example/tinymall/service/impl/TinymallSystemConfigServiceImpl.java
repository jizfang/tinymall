package com.example.tinymall.service.impl;

import com.example.tinymall.dao.TinymallSystemMapper;
import com.example.tinymall.domain.TinymallSystem;
import com.example.tinymall.domain.TinymallSystemExample;
import com.example.tinymall.service.TinymallSystemConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TinymallSystemConfigServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-23 11:44
 */
@Service
public class TinymallSystemConfigServiceImpl implements TinymallSystemConfigService {

    @Resource
    private TinymallSystemMapper systemMapper;

    @Override
    public Map<String, String> queryAll() {
        TinymallSystemExample example = new TinymallSystemExample();
        example.or().andDeletedEqualTo(false);

        List<TinymallSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> systemConfigs = new HashMap<>();
        for (TinymallSystem item : systemList) {
            systemConfigs.put(item.getKeyName(), item.getKeyValue());
        }

        return systemConfigs;
    }

    @Override
    public Map<String, String> listMail() {
        TinymallSystemExample example = new TinymallSystemExample();
        example.or().andKeyNameLike("litemall_mall_%").andDeletedEqualTo(false);
        List<TinymallSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for (TinymallSystem system : systemList) {
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    @Override
    public Map<String, String> listWx() {
        TinymallSystemExample example = new TinymallSystemExample();
        example.or().andKeyNameLike("litemall_wx_%").andDeletedEqualTo(false);
        List<TinymallSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for (TinymallSystem system : systemList) {
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    @Override
    public Map<String, String> listOrder() {
        TinymallSystemExample example = new TinymallSystemExample();
        example.or().andKeyNameLike("litemall_order_%").andDeletedEqualTo(false);
        List<TinymallSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for (TinymallSystem system : systemList) {
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    @Override
    public Map<String, String> listExpress() {
        TinymallSystemExample example = new TinymallSystemExample();
        example.or().andKeyNameLike("litemall_express_%").andDeletedEqualTo(false);
        List<TinymallSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for (TinymallSystem system : systemList) {
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    @Override
    public void updateConfig(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            TinymallSystemExample example = new TinymallSystemExample();
            example.or().andKeyNameEqualTo(entry.getKey()).andDeletedEqualTo(false);

            TinymallSystem system = new TinymallSystem();
            system.setKeyName(entry.getKey());
            system.setKeyValue(entry.getValue());
            system.setUpdateTime(LocalDateTime.now());
            systemMapper.updateByExampleSelective(system, example);
        }

    }

    @Override
    public void addConfig(String key, String value) {
        TinymallSystem system = new TinymallSystem();
        system.setKeyName(key);
        system.setKeyValue(value);
        system.setAddTime(LocalDateTime.now());
        system.setUpdateTime(LocalDateTime.now());
        systemMapper.insertSelective(system);
    }
}

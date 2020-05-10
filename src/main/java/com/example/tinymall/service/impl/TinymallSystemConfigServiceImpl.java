package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.entity.TinymallSystem;
import com.example.tinymall.mapper.TinymallSystemMapper;
import com.example.tinymall.service.TinymallSystemConfigService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName TinymallSystemConfigServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-23 11:44
 */
@Service
public class TinymallSystemConfigServiceImpl extends BaseMySqlServiceImpl<TinymallSystem,Integer> implements TinymallSystemConfigService {

    @Resource
    private TinymallSystemMapper systemMapper;

    @Override
    public Map<String, String> queryAll() {
        TinymallSystem query = new TinymallSystem();
        query.setDeleted(0);
        List<TinymallSystem> systemList = systemMapper.select(query);
        Map<String, String> systemConfigs = systemList.stream().collect(Collectors.toMap(TinymallSystem::getKeyName,TinymallSystem::getKeyValue));

        return systemConfigs;
    }

    @Override
    public Map<String, String> listMail() {
        /*TinymallSystemExample example = new TinymallSystemExample();
        example.or().andKeyNameLike("litemall_mall_%").andDeletedEqualTo(false);
        List<TinymallSystem> systemList = systemMapper.selectByExample(example);*/
        Map<String, String> data = new HashMap<>();
        /*for (TinymallSystem system : systemList) {
            data.put(system.getKeyName(), system.getKeyValue());
        }*/
        return data;
    }

    @Override
    public Map<String, String> listWx() {
        /*TinymallSystemExample example = new TinymallSystemExample();
        example.or().andKeyNameLike("litemall_wx_%").andDeletedEqualTo(false);
        List<TinymallSystem> systemList = systemMapper.selectByExample(example);*/
        Map<String, String> data = new HashMap<>();
        /*for (TinymallSystem system : systemList) {
            data.put(system.getKeyName(), system.getKeyValue());
        }*/
        return data;
    }

    @Override
    public Map<String, String> listOrder() {
        /*TinymallSystemExample example = new TinymallSystemExample();
        example.or().andKeyNameLike("litemall_order_%").andDeletedEqualTo(false);
        List<TinymallSystem> systemList = systemMapper.selectByExample(example);*/
        Map<String, String> data = new HashMap<>();
        /*for (TinymallSystem system : systemList) {
            data.put(system.getKeyName(), system.getKeyValue());
        }*/
        return data;
    }

    @Override
    public Map<String, String> listExpress() {
        /*TinymallSystemExample example = new TinymallSystemExample();
        example.or().andKeyNameLike("litemall_express_%").andDeletedEqualTo(false);
        List<TinymallSystem> systemList = systemMapper.selectByExample(example);*/
        Map<String, String> data = new HashMap<>();
        /*for (TinymallSystem system : systemList) {
            data.put(system.getKeyName(), system.getKeyValue());
        }*/
        return data;
    }

    @Override
    public void updateConfig(Map<String, String> data) {
        /*for (Map.Entry<String, String> entry : data.entrySet()) {
            TinymallSystemExample example = new TinymallSystemExample();
            example.or().andKeyNameEqualTo(entry.getKey()).andDeletedEqualTo(false);

            TinymallSystem system = new TinymallSystem();
            system.setKeyName(entry.getKey());
            system.setKeyValue(entry.getValue());
            system.setUpdateTime(LocalDateTime.now());
            systemMapper.updateByExampleSelective(system, example);
        }*/

    }
}

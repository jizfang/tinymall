package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.entity.TinymallStorage;
import com.example.tinymall.mapper.TinymallStorageMapper;
import com.example.tinymall.service.TinymallStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName TinymallStorageServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-9 17:06
 */
@Service
@Primary
public class TinymallStorageServiceImpl extends BaseMySqlServiceImpl<TinymallStorage,Integer> implements TinymallStorageService {
    @Autowired
    private TinymallStorageMapper storageMapper;

    @Override
    public TinymallStorage findByKey(String key) {
        Example example = new Example(TinymallStorage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("storage_key",key);
        List<TinymallStorage> storageList = storageMapper.selectByExample(example);
        return storageList.get(0);
    }

    @Override
    public void deleteByPk(String key) {
        TinymallStorage tinymallStorage = new TinymallStorage();
        tinymallStorage.setStorageKey(key);
        storageMapper.delete(tinymallStorage);
    }
}

package com.example.tinymall.core.storage.config;

import com.example.tinymall.core.storage.LocalStorage;
import com.example.tinymall.core.storage.StorageService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName StorageAutoConfiguration
 * @Description 存储自动配置
 * @Author jzf
 * @Date 2020-5-7 17:05
 */
@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class StorageAutoConfiguration {
    private final StorageProperties properties;

    public StorageAutoConfiguration(StorageProperties properties) {
        this.properties = properties;
    }

    @Bean
    public StorageService storageService() {
        StorageService storageService = new StorageService();
        String active = this.properties.getActive();
        storageService.setActive(active);
        if (active.equals("local")) {
            storageService.setStorage(localStorage());
        } else {
            throw new RuntimeException("当前存储模式 " + active + " 不支持");
        }

        return storageService;
    }

    @Bean
    public LocalStorage localStorage() {
        LocalStorage localStorage = new LocalStorage();
        StorageProperties.Local local = this.properties.getLocal();
        localStorage.setAddress(local.getAddress());
        localStorage.setStoragePath(local.getStoragePath());
        return localStorage;
    }
}

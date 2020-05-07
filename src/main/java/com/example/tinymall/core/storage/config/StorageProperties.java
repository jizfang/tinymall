package com.example.tinymall.core.storage.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName StorageProperties
 * @Description 存储配置文件
 * @Author jzf
 * @Date 2020-5-7 17:01
 */
@ConfigurationProperties(prefix = "tinymall.storage")
@Getter
@Setter
public class StorageProperties {
    private String active;
    private Local local;

    public static class Local {
        private String address;
        private String storagePath;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStoragePath() {
            return storagePath;
        }

        public void setStoragePath(String storagePath) {
            this.storagePath = storagePath;
        }
    }
}

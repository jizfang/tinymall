package com.example.tinymall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.example.tinymall"})
@MapperScan("com.example.tinymall.dao")
@EnableTransactionManagement
public class TinymallApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinymallApplication.class, args);
    }

}

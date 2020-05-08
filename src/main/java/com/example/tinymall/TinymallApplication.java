package com.example.tinymall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.tinymall.mapper")
@EnableTransactionManagement
public class TinymallApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinymallApplication.class, args);
    }

}

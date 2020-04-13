package com.lljz.crm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lljz.crm.mapper")
public class LjzCrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(LjzCrmApplication.class, args);
    }

}

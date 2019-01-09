package com.amaker.online;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.amaker.online.dao")
public class OnlinestudyApplication {

    public static void main(String[] args) {

        SpringApplication.run(OnlinestudyApplication.class, args);
    }

}


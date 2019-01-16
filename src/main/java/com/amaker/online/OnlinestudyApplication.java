package com.amaker.online;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.amaker.online.dao")
//@ComponentScan("com.amaker.online.config")
public class OnlinestudyApplication {

    public static void main(String[] args) {

        SpringApplication.run(OnlinestudyApplication.class, args);
    }

}


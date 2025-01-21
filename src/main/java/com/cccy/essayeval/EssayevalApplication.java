package com.cccy.essayeval;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cccy.essayeval.mapper")
public class EssayevalApplication {

    public static void main(String[] args) {
        SpringApplication.run(EssayevalApplication.class, args);
    }

}

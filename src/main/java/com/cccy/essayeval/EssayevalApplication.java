package com.cccy.essayeval;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan  // 支持Servlet组件，此项目为Filter
@SpringBootApplication
@MapperScan("com.cccy.essayeval.mapper")
public class EssayevalApplication {

    public static void main(String[] args) {
        SpringApplication.run(EssayevalApplication.class, args);
    }

}

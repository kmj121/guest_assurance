package com.vsc.guest_assurance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@RestController
@EnableTransactionManagement
@MapperScan("com.vsc.guest_assurance.dao")
public class SpringbootSwagger2Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootSwagger2Application.class, args);
    }
}
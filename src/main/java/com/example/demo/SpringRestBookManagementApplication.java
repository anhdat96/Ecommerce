package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//
//@ComponentScan({"com.example.demo.service.mapper", "com.example.demo.repository"})
@SpringBootApplication
public class SpringRestBookManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestBookManagementApplication.class, args);
    }
}

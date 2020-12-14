package com.example.demo;

import com.example.demo.config.DataBaseConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.example.demo.service.mapper"})
@SpringBootApplication(scanBasePackages = {"com.example.demo"})
@EnableConfigurationProperties({DataBaseConfiguration.class})
public class SpringRestBookManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestBookManagementApplication.class, args);
    }
}

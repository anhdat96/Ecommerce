package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Anonymous Person on 2020/11/21.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host("localhost:8080")
                .pathProvider(new RelativePathProvider(null){
                    @Override
                    public String getApplicationBasePath() {
                        return "/api";
                    }
                })
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "CRUD ProductEntity By API",
                "An service to CRUD ProductEntity from a ProductEntity repository by ProductEntity ID",
                "CRUD ProductEntity v1",
                "Terms of service",
				new Contact("Di Tuot", "https://no-url.com","test@gmail.com"),
                "License of API",
                "https://swagger.io/docs/");
    }
}

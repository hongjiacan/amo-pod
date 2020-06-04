package com.amoy.pod.support.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/3
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.amoy.pod"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "pod 接口文档",
                "swagger 接口文档",
                "API V1.0",
                "pod service",
                new Contact(
                        "Amo",
                        "https://amoy.cloud",
                        "littlepoemlittlewhite@gmail.com"),
                "APACHE",
                "http://www.apache.org/",
                Collections.emptyList());
    }
}

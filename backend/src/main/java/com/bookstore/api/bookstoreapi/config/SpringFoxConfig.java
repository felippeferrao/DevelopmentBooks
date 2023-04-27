package com.bookstore.api.bookstoreapi.config;

import org.springframework.beans.factory.annotation.Value;
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

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Value("#{new Boolean('${swagger.enable}')}")
    private boolean isSwaggerEnable;
    
    @Value("${application.version}")
    private String version;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.library.api"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfo(
                        "Book Store REST API",
                        "Book Store REST API - Documentation",
                        version,
                        "Terms of service",
                        new Contact("Felippe Ferrão", null, "felippeferrao@gmail.com"),
                        "License of API", "https://felippeferrao.pt", Collections.emptyList()))
                .enable(isSwaggerEnable);

    }
}
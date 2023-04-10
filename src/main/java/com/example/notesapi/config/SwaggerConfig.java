package com.example.notesapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;
import java.util.Date;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerSpringfoxDocket() {

        Docket docket =
                new Docket(DocumentationType.SWAGGER_2)
                        .useDefaultResponseMessages(false)
                        .pathMapping("/")
                        .apiInfo(ApiInfo.DEFAULT)
                        .forCodeGeneration(true)
                        .genericModelSubstitutes(ResponseEntity.class)
                        .ignoredParameterTypes(Pageable.class)
                        .ignoredParameterTypes(java.sql.Date.class)
                        .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                        .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
                        .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
//                        .securityContexts(Lists.newArrayList(securityContext()))
//                        .securitySchemes(Lists.newArrayList(apiKey()))
                        .useDefaultResponseMessages(false);


        docket = docket.select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).build();
        return docket;

    }
}

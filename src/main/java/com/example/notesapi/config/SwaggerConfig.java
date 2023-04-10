package com.example.notesapi.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import org.modelmapper.internal.util.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.RestController;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.AuthorizationScope;
//import springfox.documentation.service.SecurityReference;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Configuration
//@EnableSwagger3
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {
    private static final String AUTHORIZATION_HEADER = "Authorization";
//    private ApiKey apiKey() {
//        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
//    }

//   @Bean
//   public OpenAPI openAPI(){
//        return new OpenAPI()
//                .info(info());
//   }
//

//    @Configuration
//    public class OpenAPIConfig {
//
//        @Value("${bezkoder.openapi.dev-url}")
//        private String devUrl;
//
//        @Value("${bezkoder.openapi.prod-url}")
//        private String prodUrl;

        @Bean
        public OpenAPI myOpenAPI() {
            Server devServer = new Server();
            devServer.setUrl("http://localhost:8090");
            devServer.setDescription("Server URL in Development environment");

//            Server prodServer = new Server();
//            prodServer.setUrl(prodUrl);
//            prodServer.setDescription("Server URL in Production environment");

            Contact contact = new Contact();
            contact.setEmail("bezkoder@gmail.com");
            contact.setName("BezKoder");
            contact.setUrl("https://www.bezkoder.com");

            License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

            Info info = new Info()
                    .title("Tutorial Management API")
                    .version("1.0")
                    .contact(contact)
                    .description("This API exposes endpoints to manage tutorials.").termsOfService("https://www.bezkoder.com/terms")
                    .license(mitLicense);

            return new OpenAPI().info(info).servers( List.of(devServer));
//                    List.of(devServer, prodServer));
        }
//    }


//    private ApiKey apiKey() {
//        return new ApiKey("JWT", "Authorization", "header");
//    }

//    private SecurityContext securityContext() {
//        return SecurityContext.builder().securityReferences(defaultAuth()).build();
//    }

//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
//    }
//    @Bean
//    public Docket swaggerSpringfoxDocket() {
////        SWAGGER_2
////        OAS_30
//        Docket docket =
//                new Docket(DocumentationType.SWAGGER_2)
//                        .ignoredParameterTypes(Authentication.class)
//                        .useDefaultResponseMessages(false)
//                        .pathMapping("/")
//                        .apiInfo(ApiInfo.DEFAULT)
//                        .forCodeGeneration(true)
//                        .genericModelSubstitutes(ResponseEntity.class)
//                        .securitySchemes(Arrays.asList(apiKey()))
//                        .ignoredParameterTypes(Pageable.class)
//                        .ignoredParameterTypes(java.sql.Date.class)
//                        .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
//                        .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
//                        .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
//                        .useDefaultResponseMessages(false);
//
//
//        docket = docket.select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).build();
//        return docket;
//
//    }
}

package com.lambdaschool.schools.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class Swagger2Config {

    /*Configures what to document using Swagger
    * @Return A docket whcih is the primary interface for Swagger Configuration*/


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.lambdaschool.schools"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiEndPointsInfo());


        /**
         * Configures some information related to the Application for Swagger
         *
         * @return ApiInfo a Swagger object containing identification information for this application
         */
    }
        private ApiInfo apiEndPointsInfo()
        {
            return new ApiInfoBuilder().title("School Model Example")
                    .description("School Model Example")
                    .contact(new Contact("George Hatzigeorgio",
                            "http://www.lambdaschool.com",
                            "georgehatzigeorgio@gmail.com"))
                    .license("MIT")
                    .licenseUrl("https://github.com/LambdaSchool/java-school/blob/master/LICENSE")
                    .version("1.0.0")
                    .build();
        }
    }

















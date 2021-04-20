package com.adidas.userexperience.emailservice.config;

import org.springframework.stereotype.Component;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Component
@EnableSwagger2
public class SwaggerConfig {

    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any()).build().apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo(){
        return new ApiInfo(
                "Adidas Subscription Service",
                "Adidas technical coding challenge",
                "1.0",
                "Terms of service fro using Subscription Service",
                new Contact("Nnadozie Omeonu",
                        "Adidas User Experience Team",
                        "Nnadozieome@gmail.com"), "Some Licence","http://adidas.com/licence",new ArrayList<>()
        );
    }
}

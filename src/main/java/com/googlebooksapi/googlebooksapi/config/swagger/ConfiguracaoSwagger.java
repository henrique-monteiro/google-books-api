package com.googlebooksapi.googlebooksapi.config.swagger;



import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class ConfiguracaoSwagger {
	
	@Bean
	public Docket googleBooksApi() {
		return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.googlebooksapi.googlebooksapi"))
                .paths(PathSelectors.ant("/**"))
                .build()
//                .pathMapping("/")
//                .directModelSubstitute(LocalDate.class, String.class)
//                .genericModelSubstitutes(ResponseEntity.class)
//                .useDefaultResponseMessages(false)
//                .enableUrlTemplating(false)
//                .apiInfo(this.getApiInfo())
                ;
		
	}
//	
//	private ApiInfo getApiInfo() {
//        return new ApiInfo(
//                "Google Books Api",
//                "Projeto para pesquisar livros utilizando a API Google Books",
//                this.getClass().getPackage().getImplementationVersion(),
//                null,
//                null,
//                null,
//                null,
//                Collections.emptyList()
//        );
//    }

}

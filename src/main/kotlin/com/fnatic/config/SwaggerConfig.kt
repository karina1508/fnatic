package com.fnatic.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import springfox.documentation.builders.PathSelectors



@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun api(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fnatic")) //.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(apiInfo())
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("Fnatic API's")
                .description("My swagger description.")
                .version("1.0")
                .termsOfServiceUrl("http://terms.com")
                .license("Apache")
                .licenseUrl("http://license.com")
                .contact(
                        Contact(
                                "Karyna Zhadan",
                                "http://localhost:8765/api/",
                                "karynazhadan@gmail.com"
                        )
                )
                .build()
    }
}

package com.fnatic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import springfox.documentation.swagger2.annotations.EnableSwagger2
import springfox.documentation.builders.RequestHandlerSelectors

import springfox.documentation.spi.DocumentationType

import springfox.documentation.spring.web.plugins.Docket

@SpringBootApplication
@EnableSwagger2
class FnaticApplication

fun main(args: Array<String>) {
    runApplication<FnaticApplication>(*args)
}

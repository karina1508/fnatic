package com.fnatic.controller

import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception


@ControllerAdvice
class ExceptionHandlerController {
    @ExceptionHandler(value = [Exception::class])
    fun exception(exception: Exception): ResponseEntity<Any> {
        return ResponseEntity.status(500)
                .body(exception.message ?: exception.localizedMessage)
    }
}



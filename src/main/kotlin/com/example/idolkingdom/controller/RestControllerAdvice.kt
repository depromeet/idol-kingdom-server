package com.example.idolkingdom.controller

import com.example.idolkingdom.exception.UserDataNotValidException
import com.example.idolkingdom.exception.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestControllerAdvice {

    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleUserNotFoundException(ex: Exception): ResponseEntity<Any?> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(UserDataNotValidException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleUserDataNotValidException(ex: Exception): ResponseEntity<Any?> {
        return ResponseEntity(ex.message, HttpStatus.UNAUTHORIZED)
    }
}
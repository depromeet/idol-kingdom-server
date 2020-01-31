package com.example.idolkingdom.controller

import com.example.idolkingdom.exception.DataNotFoundException
import com.example.idolkingdom.exception.UnauthorizedException
import com.example.idolkingdom.exception.UserDataNotValidException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestControllerAdvice {

    @ExceptionHandler(DataNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleDataNotFoundException(ex: Exception): ResponseEntity<Any?> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(UserDataNotValidException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleUserDataNotValidException(ex: Exception): ResponseEntity<Any?> {
        return ResponseEntity(ex.message, HttpStatus.UNAUTHORIZED)
    }


    @ExceptionHandler(UnauthorizedException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleUnauthorizedException(ex: Exception): ResponseEntity<Any?> {
        return ResponseEntity(ex.message, HttpStatus.UNAUTHORIZED)
    }
}
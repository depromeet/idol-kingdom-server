package com.example.idolkingdom.controller;

import com.example.idolkingdom.dto.EmailDto
import com.example.idolkingdom.dto.LoginRequestDto
import com.example.idolkingdom.dto.LoginResponseDto
import com.example.idolkingdom.dto.UserDto
import com.example.idolkingdom.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class UserController(@Autowired private val userService: UserService) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequestDto: LoginRequestDto): ResponseEntity<LoginResponseDto> =
        ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.login(loginRequestDto))

    @PostMapping("/users/validation")
    fun validateEmail(@RequestBody email: EmailDto): ResponseEntity<String> = ResponseEntity(
        if (userService.validateEmail(email))
            HttpStatus.OK
        else HttpStatus.FORBIDDEN
    )

    @PostMapping("/users")
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<LoginResponseDto> =
        ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto))
}

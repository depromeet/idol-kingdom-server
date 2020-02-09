package com.example.idolkingdom.controller;

import com.example.idolkingdom.dto.*
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
    fun validateEmail(@RequestBody email: EmailDto): ResponseEntity<EmailDto> = ResponseEntity.status(
        if (userService.validateEmail(email))
            HttpStatus.OK
        else HttpStatus.FORBIDDEN
    ).body(email)

    @PostMapping("/users")
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<LoginResponseDto> =
        ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto))

    @GetMapping("/users")
    fun getUser(@RequestParam userId: Long): ResponseEntity<UserResponseDto> =
        ResponseEntity.status(HttpStatus.OK).body(userService.getUser(userId))

    @GetMapping("/me")
    fun getMe(@RequestAttribute("id") id: Long): ResponseEntity<UserResponseDto> =
        ResponseEntity.status(HttpStatus.CREATED).body(userService.getUser(id))
}

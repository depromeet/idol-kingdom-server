package com.example.idolkingdom.controller;

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
    fun login(@RequestBody loginRequestDto: LoginRequestDto):
            ResponseEntity<LoginResponseDto> {
        val loginResponseDto: LoginResponseDto = userService.login(loginRequestDto)
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(loginResponseDto)
    }

    @PostMapping("/users")
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<String> {
        userService.createUser(userDto)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PutMapping("/users")
    fun updateUser(@RequestBody userDto: UserDto): ResponseEntity<String> {
        userService.updateUser(userDto);
        return ResponseEntity(HttpStatus.ACCEPTED)
    }


}

package com.example.idolkingdom.service

import com.example.idolkingdom.dto.EmailDto
import com.example.idolkingdom.dto.LoginRequestDto
import com.example.idolkingdom.dto.LoginResponseDto
import com.example.idolkingdom.dto.UserDto
import com.example.idolkingdom.model.User

interface UserService {
    fun createUser(userDto: UserDto): LoginResponseDto
    fun validateEmail(email: EmailDto): Boolean
    fun login(loginRequestDto: LoginRequestDto): LoginResponseDto
}
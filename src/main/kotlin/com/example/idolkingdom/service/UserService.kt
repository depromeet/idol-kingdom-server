package com.example.idolkingdom.service

import com.example.idolkingdom.dto.*

interface UserService {
    fun createUser(userDto: UserDto): LoginResponseDto
    fun validateEmail(email: EmailDto): Boolean
    fun login(loginRequestDto: LoginRequestDto): LoginResponseDto
    fun getUser(userId: Long): UserResponseDto
}
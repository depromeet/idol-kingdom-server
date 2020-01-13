package com.example.idolkingdom.service

import com.example.idolkingdom.dto.UserDto

interface UserService {
    fun createUser(userDto: UserDto)
}
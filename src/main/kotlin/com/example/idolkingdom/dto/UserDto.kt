package com.example.idolkingdom.dto

import com.example.idolkingdom.model.User

class UserDto(
        val email: String,
        val password: String,
        val name: String
) {
    fun toUser(): User {
        return User(
                email = this.email,
                password = this.password,
                name = this.name
        )
    }
}
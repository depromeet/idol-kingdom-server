package com.example.idolkingdom.dto

import com.example.idolkingdom.model.User

class UserDto(
    val email: String,
    var password: String,
    val name: String,
    val nickName: String
) {
    fun toUser(): User {
        return User(
            email = this.email,
            password = this.password,
            name = this.name,
            nickName = this.nickName
        )
    }
}
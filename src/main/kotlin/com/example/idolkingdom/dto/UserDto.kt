package com.example.idolkingdom.dto

import com.example.idolkingdom.model.User

data class UserDto(
    val email: String?,
    var password: String?,
    val nickName: String?,
    val schools: List<Long>?,
    val idols: List<Long>?
)

fun User.toDto() = UserDto(
    email, null, nickName, schools.mapNotNull { it.id }, idols.mapNotNull { it.id }
)


package com.example.idolkingdom.dto

import com.example.idolkingdom.model.User
import java.time.LocalDateTime

data class UserDto(
    val email: String?,
    var password: String?,
    val nickName: String?,
    val schools: List<Long>?,
    val idols: List<Long>?,
    val restBallotsCount: Int? = 0,
    val lastAttendantDate: LocalDateTime? = null
)

fun User.toDto() = UserDto(
    email, null, nickName, schools.mapNotNull { it.id }, idols.mapNotNull { it.id }, restBallotCount, lastAttendantDate
)

fun User.toOutSiderDto() = UserDto(
    null, null, nickName, schools.mapNotNull { it.id }, idols.mapNotNull { it.id }, null, null
)

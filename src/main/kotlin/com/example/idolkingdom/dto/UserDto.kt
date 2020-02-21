package com.example.idolkingdom.dto

import com.example.idolkingdom.model.User
import java.time.LocalDateTime

data class UserDto(
    val id: Long?,
    val email: String?,
    var password: String?,
    val nickName: String?,
    val profileImage: String?,
    val schools: List<Long?>?,
    val idols: List<Long?>?,
    var ballotList: List<Long?>?,
    val restBallotsCount: Int? = 0,
    val lastAttendantDate: LocalDateTime? = null
)

fun User.toDto() = UserDto(
    id, email, null, nickName, profileImage, schools.mapNotNull { it.id }, idols.mapNotNull { it.id }, ballots.map { b -> b.id }, restBallotCount, lastAttendantDate
)

fun User.toOutSiderDto() = UserDto(
    id, null, null, nickName, profileImage, schools.mapNotNull { it.id }, idols.mapNotNull { it.id }, null, null, null
)

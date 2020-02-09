package com.example.idolkingdom.dto

data class UserResponseDto(
    val id : Long?,
    val email: String,
    val nickName: String,
    val schoolList: List<Long?>,
    val idolIdList: List<Long?>,
    var ballotList: List<Long?>
)
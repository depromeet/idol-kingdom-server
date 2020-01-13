package com.example.idolkingdom.model

import java.time.ZonedDateTime

data class User(
    val id: Int,
    val email: String,
    val password: String,
    val name: String,
    val school: School,
    val lovedGroups: List<IdolGroup>,
    val votedList: List<Vote>,
    val createdAt: ZonedDateTime
)

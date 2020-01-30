package com.example.idolkingdom.dto

import com.example.idolkingdom.model.Location
import com.example.idolkingdom.model.School
import com.example.idolkingdom.model.User
import com.example.idolkingdom.model.Vote
import javax.persistence.*

data class SchoolDto(
    val id: Long? = null,
    val name: String,
    val address: String,
    val location: Location,
    val level: School.Level,
    val users: List<User>,
    val lastVote: Vote
)
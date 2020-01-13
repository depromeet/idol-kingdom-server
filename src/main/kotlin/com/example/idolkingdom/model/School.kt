package com.example.idolkingdom.model

data class School(
    val id: String,
    val name: String,
    val location: Location,
    val level: SchoolLevel,
    val users: List<User>
)

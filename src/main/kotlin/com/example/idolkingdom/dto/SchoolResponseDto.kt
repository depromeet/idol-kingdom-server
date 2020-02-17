package com.example.idolkingdom.dto

import com.example.idolkingdom.model.Location
import com.example.idolkingdom.model.School
import com.example.idolkingdom.model.Vote

data class SchoolResponseDto(
    val id: Long?,
    val name: String,
    val address: String,
    val location: Location,
    val level: School.Level,
    var users: List<Long?>,
    var markerImage: String?,
    var ballots: List<Long?> = emptyList()
) {
    companion object {
        fun of(school: School): SchoolResponseDto {
            return SchoolResponseDto(
                id = school.id,
                name = school.name,
                address = school.address,
                location = school.location,
                level = school.level,
                users = school.users.map { u -> u.id },
                markerImage = null
            )
        }
    }
}
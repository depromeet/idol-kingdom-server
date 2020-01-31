package com.example.idolkingdom.dto

import com.example.idolkingdom.model.Location
import com.example.idolkingdom.model.School

class SchoolResponseDto(
    val id: Long?,
    val name: String,
    val address: String,
    val location: Location,
    val level: School.Level,
    var users: List<Long?>
) {
    companion object {
        fun of(school: School): SchoolResponseDto {
            return SchoolResponseDto(
                id = school.id,
                name = school.name,
                address = school.address,
                location = school.location,
                level = school.level,
                users = school.users.map { u -> u.id }
            )
        }
    }
}
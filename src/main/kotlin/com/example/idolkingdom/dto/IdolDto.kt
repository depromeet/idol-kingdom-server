package com.example.idolkingdom.dto

import com.example.idolkingdom.model.Idol
import com.example.idolkingdom.model.IdolGroup
import com.example.idolkingdom.model.Image
import java.time.LocalDate

data class IdolDto(
    val id: Long,
    val name: String,
    var members: List<Member> = listOf(),
    var images: List<String> = listOf()
) {
    data class Member(
        val id: Long,
        val name: String,
        val bloodType: Idol.BloodType,
        val entertainment: String?,
        val graduation: String,
        val dateOfBirth: LocalDate?,
        val hometown: String,
        val images: List<Image> = listOf()
    )
}

fun IdolGroup.toDto() = IdolDto(
    id!!,
    name,
    members.map {
        IdolDto.Member(it.id!!, it.name, it.bloodType, it.entertainment?.name, it.graduation, it.dateOfBirth, it.hometown, it.images)
    },
    images.map { it.url }
)
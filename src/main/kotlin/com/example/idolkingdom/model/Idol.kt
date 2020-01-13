package com.example.idolkingdom.model

import java.time.ZonedDateTime

data class Idol(
    val id: Int,
    val name: String,
    val description: String,
    val bloodType: BloodType,
    val groups: List<IdolGroup>,
    val entertainment: Entertainment,
    val graduation: String,
    val dateOfBirth: ZonedDateTime,
    val hometown: String,
    val images: List<String>
)

package com.example.idolkingdom.model

import java.util.*

data class Idol(
    val id: Int,
    val name: String,
    val description: String,
    val bloodType: BloodType,
    val groups: List<IdolGroup>,
    val entertainment: Entertainment,
    val graduation: String,
    val dateOfBirth: Date,
    val hometown: String,
    val images: List<String>
) {

    enum class BloodType {
        A, B, AB, O
    }
}

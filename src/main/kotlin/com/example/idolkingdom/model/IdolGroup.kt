package com.example.idolkingdom.model

data class IdolGroup(
    val id: Int,
    val name: String,
    val description: String,
    val members: List<Idol>,
    val images: List<String>,
    val votes: List<Vote>
)

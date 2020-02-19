package com.example.idolkingdom.dto

import com.example.idolkingdom.model.IdolGroup

data class IdolDto(
    val id: Long,
    val name: String,
    val markerImage: String?,
    val circleImage: String?,
    var images: List<String> = listOf(),
    var currentBallots: List<Long?>?
) {
}

fun IdolGroup.toDto(currentBallots: List<Long?>) = IdolDto(
    id!!,
    name,
    images.find { it.url.contains("marker")}?.url,
    images.find { it.url.contains("circle")}?.url,
    images.map { it.url },
    currentBallots
)
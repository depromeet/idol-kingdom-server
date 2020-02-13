package com.example.idolkingdom.controller.response

import com.example.idolkingdom.dto.IdolDto

class IdolGroupResponse(
    val idols: List<IdolGroup>
) {
    data class IdolGroup(
        val id: Long?,
        val name: String,
        val markerImage: String?,
        val circleImage: String?,
        var images: List<String>,
        var currentBallots: List<Long?>
    ) {
        companion object {
            fun of(dto: IdolDto): IdolGroup {
                return IdolGroup(
                    id = dto.id,
                    name = dto.name,
                    markerImage = dto.markerImage,
                    circleImage = dto.circleImage,
                    images = dto.images,
                    currentBallots = dto.currentBallots
                )
            }
        }
    }
}
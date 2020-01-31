package com.example.idolkingdom.dto

import com.example.idolkingdom.model.IdolGroup

class IdolGroupResponseDto(
    val id: Long?,
    val name: String,
    var members: List<Long?>,
    var images: List<Long?>,
    var fans: List<Long?>,
    var ballots: List<Long?>
) {
    companion object {
        fun of(idolGroup: IdolGroup): IdolGroupResponseDto {
            return IdolGroupResponseDto(
                id = idolGroup.id,
                name = idolGroup.name,
                members = idolGroup.members.map { m -> m.id },
                images = idolGroup.images.map { image -> image.id },
                fans = idolGroup.fans.map { fan -> fan.id },
                ballots = idolGroup.ballots.map { b -> b.id }
            )
        }
    }
}
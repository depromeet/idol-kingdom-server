package com.example.idolkingdom.controller.response

import com.example.idolkingdom.dto.toDto
import com.example.idolkingdom.model.Ballot
import java.time.LocalDateTime

class BallotResponse(
    val id: Long?,
    val user: Long?,
    val idol: IdolGroupResponse.IdolGroup,
    val vote: Long?,
    val date: LocalDateTime
) {
    companion object {
        fun of(ballot: Ballot) = BallotResponse(
            ballot.id, ballot.user.id, IdolGroupResponse.IdolGroup.of(ballot.idol.toDto(emptyList())), ballot.vote.id, ballot.date
        )
    }
}
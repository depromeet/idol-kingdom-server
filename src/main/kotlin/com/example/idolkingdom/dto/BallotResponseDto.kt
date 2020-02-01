package com.example.idolkingdom.dto

import com.example.idolkingdom.model.Ballot
import java.time.LocalDateTime

class BallotResponseDto(
    val id: Long?,
    val user: Long?,
    val idol: IdolGroupResponseDto,
    val vote: Long?,
    val date: LocalDateTime
) {
    companion object {
        fun of(ballot: Ballot) = BallotResponseDto(
            ballot.id, ballot.user.id, IdolGroupResponseDto.of(ballot.idol), ballot.vote.id, ballot.date
        )
    }
}
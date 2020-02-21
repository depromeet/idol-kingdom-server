package com.example.idolkingdom.dto

import com.example.idolkingdom.controller.response.BallotResponse
import com.example.idolkingdom.model.Vote
import java.time.LocalDateTime

class VoteResponseDto(
    val id: Long?,
    val title: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val ballotIds: List<Long?>,
    val myBallots: List<BallotResponse>?
) {
    companion object {
        fun of(vote: Vote, myBallots: List<BallotResponse>? = null): VoteResponseDto = VoteResponseDto(
            id = vote.id,
            title = vote.title,
            startDate = vote.startDate,
            endDate = vote.endDate,
            ballotIds = vote.ballots.map { it.id },
            myBallots = myBallots
        )

    }
}
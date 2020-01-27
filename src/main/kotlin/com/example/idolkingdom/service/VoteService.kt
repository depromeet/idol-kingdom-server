package com.example.idolkingdom.service

import com.example.idolkingdom.dto.BallotRequestDto
import com.example.idolkingdom.dto.VoteRequestDto
import com.example.idolkingdom.model.Ballot
import com.example.idolkingdom.model.Vote

interface VoteService {
    fun createVote(dto: VoteRequestDto): Vote
    fun deleteVote(voteId: Long): String
    fun castBallot(dto: BallotRequestDto): Ballot
}
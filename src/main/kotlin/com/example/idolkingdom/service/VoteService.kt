package com.example.idolkingdom.service

import com.example.idolkingdom.dto.BallotRequestDto
import com.example.idolkingdom.dto.VoteRequestDto
import com.example.idolkingdom.dto.VoteResponseDto
import com.example.idolkingdom.model.Ballot

interface VoteService {
    fun createVote(dto: VoteRequestDto): VoteResponseDto
    fun deleteVote(voteId: Long): String
    fun castBallot(dto: BallotRequestDto): Ballot
    fun getVoteList(): List<VoteResponseDto>
}
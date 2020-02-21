package com.example.idolkingdom.service

import com.example.idolkingdom.controller.request.BallotRequest
import com.example.idolkingdom.controller.response.BallotResponse
import com.example.idolkingdom.dto.VoteRequestDto
import com.example.idolkingdom.dto.VoteResponseDto

interface VoteService {
    fun createVote(dto: VoteRequestDto): VoteResponseDto
    fun deleteVote(voteId: Long): String
    fun castBallot(id: Long, request: BallotRequest): BallotResponse
    fun getVoteList(): List<VoteResponseDto>
    fun get(voteId: Long): VoteResponseDto
    fun getBallots(ballotIds: List<Long>): List<BallotResponse>
    fun getCurrentVote(id : Long) : VoteResponseDto
}
package com.example.idolkingdom.service.impl

import com.example.idolkingdom.controller.request.BallotRequest
import com.example.idolkingdom.controller.response.BallotResponse
import com.example.idolkingdom.controller.response.IdolGroupResponse
import com.example.idolkingdom.dto.IdolDto
import com.example.idolkingdom.dto.VoteRequestDto
import com.example.idolkingdom.dto.VoteResponseDto
import com.example.idolkingdom.dto.toDto
import com.example.idolkingdom.model.Ballot
import com.example.idolkingdom.model.Vote
import com.example.idolkingdom.repository.BallotRepository
import com.example.idolkingdom.repository.IdolGroupRepository
import com.example.idolkingdom.repository.UserRepository
import com.example.idolkingdom.repository.VoteRepository
import com.example.idolkingdom.service.VoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

@Service
class VoteServiceImpl(@Autowired private val voteRepository: VoteRepository,
                      @Autowired private val ballotRepository: BallotRepository,
                      @Autowired private val idolGroupRepository: IdolGroupRepository,
                      @Autowired private val userRepository: UserRepository) : VoteService {

    override fun createVote(dto: VoteRequestDto): VoteResponseDto =
        VoteResponseDto.of(
            voteRepository.save(
                Vote(
                    title = dto.title,
                    startDate = dto.startDate,
                    endDate = dto.endDate
                )
            )
        )

    override fun getVoteList(): List<VoteResponseDto> =
        voteRepository.findAll().map { vote -> VoteResponseDto.of(vote) }

    override fun get(voteId: Long): VoteResponseDto = VoteResponseDto.of(voteRepository.getOne(voteId))

    override fun getCurrentVote(id: Long): VoteResponseDto {
        val user = userRepository.getOne(id)
        val vote = voteRepository.findTopByOrderByIdDesc()
        return VoteResponseDto.of(
            vote,
            vote.ballots.filter { it.user.id == user.id }.map(BallotResponse.Companion::of)
        )
    }

    override fun getCurrentRank(): List<IdolDto> = voteRepository.findTopByOrderByIdDesc()
        .ballots
        .groupBy { it.idol }
        .map { it.key.toDto(it.value.mapNotNull { it.id }) }
        .sortedByDescending { it.currentBallots?.size }

    override fun deleteVote(voteId: Long): String {
        voteRepository.deleteById(voteId)
        return "success"
    }

    override fun castBallot(id: Long, request: BallotRequest): BallotResponse {
        val user = userRepository.findById(id).get()
        if (user.restBallotCount <= 0)
            throw IllegalStateException("restBallotCount is ${user.restBallotCount}")
        val idol = idolGroupRepository.findById(request.idolId).get()
        val vote = voteRepository.findById(request.voteId).get()
        user.restBallotCount -= 1
        userRepository.save(user)
        return BallotResponse.of(
            ballotRepository.save(
                Ballot(
                    user = user,
                    idol = idol,
                    vote = vote)
            )
        )
    }

    override fun getBallots(ballotIds: List<Long>): List<BallotResponse> = ballotRepository.findAllById(ballotIds)
        .map { BallotResponse.of(it) }
}

package com.example.idolkingdom.service.impl

import com.example.idolkingdom.dto.BallotRequestDto
import com.example.idolkingdom.dto.BallotResponseDto
import com.example.idolkingdom.dto.VoteRequestDto
import com.example.idolkingdom.dto.VoteResponseDto
import com.example.idolkingdom.model.Ballot
import com.example.idolkingdom.model.Vote
import com.example.idolkingdom.repository.BallotRepository
import com.example.idolkingdom.repository.IdolGroupRepository
import com.example.idolkingdom.repository.UserRepository
import com.example.idolkingdom.repository.VoteRepository
import com.example.idolkingdom.service.VoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

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

    override fun deleteVote(voteId: Long): String {
        voteRepository.deleteById(voteId)
        return "success"
    }

    override fun castBallot(dto: BallotRequestDto): BallotResponseDto {
        val user = userRepository.findById(dto.userId).get()
        val idol = idolGroupRepository.findById(dto.idolId).get()
        val vote = voteRepository.findById(dto.voteId).get()

        return BallotResponseDto.of(
            ballotRepository.save(
                Ballot(
                    user = user,
                    idol = idol,
                    vote = vote)
            )
        )
    }

    override fun getBallots(ballotIds: List<Long>): List<BallotResponseDto> = ballotRepository.findAllById(ballotIds)
        .map { BallotResponseDto.of(it) }
}

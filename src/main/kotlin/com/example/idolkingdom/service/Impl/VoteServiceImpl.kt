package com.example.idolkingdom.service.Impl

import com.example.idolkingdom.dto.BallotRequestDto
import com.example.idolkingdom.dto.VoteRequestDto
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
    override fun createVote(dto: VoteRequestDto): Vote {
        return voteRepository.save(
            Vote(
                title = dto.title,
                startDate = dto.startDate,
                endDate = dto.endDate
            )
        )
    }

    override fun deleteVote(voteId: Long) {
        voteRepository.deleteById(voteId)
    }

    override fun castBallot(dto: BallotRequestDto): Ballot {
        val user = userRepository.findById(dto.userId).get()
        val idol = idolGroupRepository.findById(dto.idolId).get()
        val vote = voteRepository.findById(dto.voteId).get()

        return ballotRepository.save(
            Ballot(
                user = user,
                idol = idol,
                vote = vote)
        )

    }
}
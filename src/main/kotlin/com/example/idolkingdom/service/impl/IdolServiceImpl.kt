package com.example.idolkingdom.service.impl

import com.example.idolkingdom.dto.IdolDto
import com.example.idolkingdom.dto.toDto
import com.example.idolkingdom.repository.IdolGroupRepository
import com.example.idolkingdom.repository.VoteRepository
import com.example.idolkingdom.service.IdolService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class IdolServiceImpl(
    @Autowired private val idolRepository: IdolGroupRepository,
    @Autowired private val voteRepository: VoteRepository
) : IdolService {

    override fun getAll(): List<IdolDto> {
        val currentVote = voteRepository.findTopByOrderByIdDesc()
        return idolRepository.findAll().map {
            it.toDto(
                it.ballots
                    .filter { it.vote.id == currentVote.id }
                    .map { it.id }
            )
        }
    }

    override fun get(name: String): List<IdolDto> {
        val currentVote = voteRepository.findTopByOrderByIdDesc()
        return idolRepository.findByName(name).map {
            it.toDto(
                it.ballots
                    .filter { it.vote.id == currentVote.id }
                    .map { it.id }
            )
        }
    }

    override fun get(idolGroup: Long): IdolDto {
        val currentVote = voteRepository.findTopByOrderByIdDesc()
        val idol = idolRepository.findById(idolGroup).get()
        return idol.toDto(
            idol.ballots
                .filter { it.vote.id == currentVote.id }
                .map { it.id }
        )
    }

    override fun search(query: String): List<IdolDto> {
        val currentVote = voteRepository.findTopByOrderByIdDesc()
        return idolRepository.findByNameLike(query).map {
            it.toDto(
                it.ballots
                    .filter { it.vote.id == currentVote.id }
                    .map { it.id }
            )
        }
    }
}
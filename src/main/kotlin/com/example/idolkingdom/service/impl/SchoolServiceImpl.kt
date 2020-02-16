package com.example.idolkingdom.service.impl

import com.example.idolkingdom.dto.IdolDto
import com.example.idolkingdom.dto.LocationDto
import com.example.idolkingdom.dto.SchoolResponseDto
import com.example.idolkingdom.dto.toDto
import com.example.idolkingdom.exception.DataNotFoundException
import com.example.idolkingdom.repository.SchoolRepository
import com.example.idolkingdom.repository.VoteRepository
import com.example.idolkingdom.service.SchoolService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SchoolServiceImpl(
    @Autowired private val schoolRepository: SchoolRepository,
    @Autowired private val voteRepository: VoteRepository
) : SchoolService {

    override fun getAll(): List<SchoolResponseDto> = schoolRepository.findAll().map { SchoolResponseDto.of(it) }

    override fun get(name: String): List<SchoolResponseDto> = schoolRepository.findByName(name).map { SchoolResponseDto.of(it) }

    override fun get(schoolId: Long): SchoolResponseDto = SchoolResponseDto.of(schoolRepository.findById(schoolId).get())

    override fun get(schoolIds: List<Long>): List<SchoolResponseDto> = schoolRepository.findAllById(schoolIds).map { SchoolResponseDto.of(it) }

    override fun search(query: String, size: Int?): List<SchoolResponseDto> {
        val lastVote = voteRepository.findAll().lastOrNull() ?: throw DataNotFoundException("vote not found")
        return schoolRepository.findByNameLike(query).run {
            size?.let { take(it) } ?: this
        }.map {
            SchoolResponseDto.of(it).apply {
                ballots = lastVote.ballots.filter { users.contains(it.user.id) }.map { it.id }
            }
        }
    }

    override fun search(startLocation: LocationDto, endLocation: LocationDto, size: Int?): List<SchoolResponseDto> {
        val lastVote = voteRepository.findAll().lastOrNull() ?: throw DataNotFoundException("vote not found")
        val startX = Math.min(startLocation.latitude, endLocation.latitude)
        val startY = Math.min(startLocation.longitude, endLocation.longitude)
        val endX = Math.max(startLocation.latitude, endLocation.latitude)
        val endY = Math.max(startLocation.longitude, endLocation.longitude)
        return schoolRepository.findByLocation(
            Math.min(startLocation.latitude, endLocation.latitude),
            Math.min(startLocation.longitude, endLocation.longitude),
            Math.max(startLocation.latitude, endLocation.latitude),
            Math.max(startLocation.longitude, endLocation.longitude)
        ).run {
            size?.let {
                sortedBy { (it.location.latitude - (endX + startX) / 2) + (it.location.longitude - (endY + startY) / 2) }
                    .take(it)
            } ?: this
        }.map {
            SchoolResponseDto.of(it).apply {
                ballots = lastVote.ballots.filter { users.contains(it.user.id) }.map { it.id }
            }
        }
    }

    override fun getRank(schoolId: Long): List<IdolDto> {
        val school = schoolRepository.getOne(schoolId)
        val students = school.users.map { it.id }
        return voteRepository.findTopByOrderByIdDesc().ballots.filter { students.contains(it.user.id) }
            .groupBy { it.idol }
            .map { it.key.toDto(it.value.mapNotNull { it.id }) }
            .sortedByDescending { it.currentBallots.size }
    }
}

package com.example.idolkingdom.service.impl

import com.example.idolkingdom.dto.LocationDto
import com.example.idolkingdom.model.School
import com.example.idolkingdom.repository.SchoolRepository
import com.example.idolkingdom.service.SchoolService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SchoolServiceImpl(@Autowired private val schoolRepository: SchoolRepository) : SchoolService {

    override fun getAll(): List<School> = schoolRepository.findAll()

    override fun get(name: String): List<School> = schoolRepository.findByName(name)

    override fun search(query: String, size: Int?): List<School> = schoolRepository.findByNameLike(query).run {
        size?.let { take(it) } ?: this
    }

    override fun search(startLocation: LocationDto, endLocation: LocationDto, size: Int?): List<School> {
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
        }
    }
}
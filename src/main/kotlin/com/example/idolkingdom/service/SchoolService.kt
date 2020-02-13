package com.example.idolkingdom.service

import com.example.idolkingdom.dto.IdolDto
import com.example.idolkingdom.dto.LocationDto
import com.example.idolkingdom.dto.SchoolResponseDto

interface SchoolService {
    fun getAll(): List<SchoolResponseDto>
    fun get(name: String): List<SchoolResponseDto>
    fun get(schoolId: Long): SchoolResponseDto
    fun get(schoolIds: List<Long>): List<SchoolResponseDto>
    fun search(query: String, size: Int?): List<SchoolResponseDto>
    fun search(startLocation: LocationDto, endLocation: LocationDto, size: Int?): List<SchoolResponseDto>
    fun getRank(schoolId: Long): List<IdolDto>
}
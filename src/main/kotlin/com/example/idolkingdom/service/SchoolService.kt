package com.example.idolkingdom.service

import com.example.idolkingdom.dto.BallotResponseDto
import com.example.idolkingdom.dto.IdolDto
import com.example.idolkingdom.dto.SchoolResponseDto
import com.example.idolkingdom.dto.LocationDto
import com.example.idolkingdom.model.Ballot
import com.example.idolkingdom.model.School

interface SchoolService {
    fun getAll(): List<SchoolResponseDto>
    fun get(name: String): List<SchoolResponseDto>
    fun get(schoolId: Long): SchoolResponseDto
    fun get(schoolIds: List<Long>): List<SchoolResponseDto>
    fun search(query: String, size: Int?): List<SchoolResponseDto>
    fun search(startLocation: LocationDto, endLocation: LocationDto, size: Int?): List<SchoolResponseDto>
    fun getRank(schoolId: Long): List<Pair<IdolDto, List<Long>>>
}
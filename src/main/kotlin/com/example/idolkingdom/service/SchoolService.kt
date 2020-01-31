package com.example.idolkingdom.service

import com.example.idolkingdom.dto.SchoolResponseDto
import com.example.idolkingdom.model.School

interface SchoolService {
    fun getAll(): List<School>
    fun get(name: String): List<School>
    fun search(query: String): List<School>
    fun get(schoolId: Long): SchoolResponseDto
}
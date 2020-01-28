package com.example.idolkingdom.service

import com.example.idolkingdom.dto.LocationDto
import com.example.idolkingdom.model.School

interface SchoolService {
    fun getAll(): List<School>
    fun get(name: String): List<School>
    fun search(query: String, size: Int?): List<School>
    fun search(startLocation: LocationDto, endLocation: LocationDto, size: Int?): List<School>
}
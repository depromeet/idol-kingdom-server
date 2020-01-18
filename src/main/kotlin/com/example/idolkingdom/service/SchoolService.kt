package com.example.idolkingdom.service

import com.example.idolkingdom.model.School

interface SchoolService {
    fun getAll(): List<School>
    fun get(name: String): List<School>
    fun search(query: String): List<School>
}
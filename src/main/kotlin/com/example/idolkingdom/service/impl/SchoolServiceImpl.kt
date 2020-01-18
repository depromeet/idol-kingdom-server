package com.example.idolkingdom.service.impl

import com.example.idolkingdom.model.School
import com.example.idolkingdom.repository.SchoolRepository
import com.example.idolkingdom.service.SchoolService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SchoolServiceImpl(@Autowired private val schoolRepository: SchoolRepository) : SchoolService {

    override fun getAll(): List<School> = schoolRepository.findAll()

    override fun get(name: String): List<School> = schoolRepository.findByName(name)

    override fun search(query: String): List<School> = schoolRepository.findByNameLike(query)
}
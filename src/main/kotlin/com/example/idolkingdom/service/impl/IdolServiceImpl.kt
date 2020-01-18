package com.example.idolkingdom.service.impl

import com.example.idolkingdom.model.IdolGroup
import com.example.idolkingdom.repository.IdolGroupRepository
import com.example.idolkingdom.service.IdolService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class IdolServiceImpl(@Autowired private val idolRepository: IdolGroupRepository) : IdolService {

    override fun getAll(): List<IdolGroup> = idolRepository.findAll()

    override fun get(name: String): List<IdolGroup> = idolRepository.findByName(name)

    override fun search(query: String): List<IdolGroup> = idolRepository.findByNameLike(query)
}
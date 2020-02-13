package com.example.idolkingdom.service


import com.example.idolkingdom.dto.IdolDto

interface IdolService {
    fun getAll(): List<IdolDto>
    fun get(name: String): List<IdolDto>
    fun search(query: String): List<IdolDto>
    fun get(idolGroup: Long): IdolDto
}
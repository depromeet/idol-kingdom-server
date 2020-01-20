package com.example.idolkingdom.service

import com.example.idolkingdom.model.IdolGroup

interface IdolService {
    fun getAll(): List<IdolGroup>
    fun get(name: String): List<IdolGroup>
    fun search(query: String): List<IdolGroup>
}
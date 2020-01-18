package com.example.idolkingdom.repository

import com.example.idolkingdom.model.IdolGroup
import org.springframework.data.jpa.repository.JpaRepository


interface IdolGroupRepository : JpaRepository<IdolGroup, Long> {
    fun findByName(name: String): List<IdolGroup>
    fun findByNameLike(name: String): List<IdolGroup>
}
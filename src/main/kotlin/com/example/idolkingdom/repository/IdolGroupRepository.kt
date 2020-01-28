package com.example.idolkingdom.repository

import com.example.idolkingdom.model.IdolGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface IdolGroupRepository : JpaRepository<IdolGroup, Long> {
    fun findByName(name: String): List<IdolGroup>
    @Query("SELECT i FROM IdolGroup i WHERE i.name LIKE %?1%")
    fun findByNameLike(name: String): List<IdolGroup>
}
package com.example.idolkingdom.repository

import com.example.idolkingdom.model.School
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.math.BigDecimal


interface SchoolRepository : JpaRepository<School, Long> {
    fun findByName(name: String): List<School>
    @Query("SELECT s FROM School s WHERE s.name LIKE %?1%")
    fun findByNameLike(name: String): List<School>

    @Query("SELECT s FROM School s WHERE s.location.latitude BETWEEN ?1 AND ?3 AND s.location.longitude BETWEEN ?2 AND ?4")
    fun findByLocation(
        startOfLatitude: Float,
        startOfLongitude: Float,
        endOfLatitude: Float,
        endOfLongitude: Float
    ): List<School>
}
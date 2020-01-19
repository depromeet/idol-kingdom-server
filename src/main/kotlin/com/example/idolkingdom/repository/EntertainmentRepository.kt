package com.example.idolkingdom.repository

import com.example.idolkingdom.model.Entertainment
import org.springframework.data.jpa.repository.JpaRepository


interface EntertainmentRepository : JpaRepository<Entertainment, Long>{
    fun findByName(name: String): List<Entertainment>
}

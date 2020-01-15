package com.example.idolkingdom.repository

import com.example.idolkingdom.model.School
import org.springframework.data.jpa.repository.JpaRepository

interface SchoolRepository : JpaRepository<School, Int> {
}
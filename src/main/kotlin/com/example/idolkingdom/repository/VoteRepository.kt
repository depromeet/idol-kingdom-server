package com.example.idolkingdom.repository

import com.example.idolkingdom.model.Vote
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface VoteRepository : JpaRepository<Vote, Long> {
    fun findTopByOrderByIdDesc(): Vote
}
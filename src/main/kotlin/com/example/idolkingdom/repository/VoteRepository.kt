package com.example.idolkingdom.repository

import com.example.idolkingdom.model.Vote
import org.springframework.data.jpa.repository.JpaRepository

interface VoteRepository : JpaRepository<Vote, Long> {
}
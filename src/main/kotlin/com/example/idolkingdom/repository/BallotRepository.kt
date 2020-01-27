package com.example.idolkingdom.repository

import com.example.idolkingdom.model.Ballot
import org.springframework.data.jpa.repository.JpaRepository

interface BallotRepository : JpaRepository<Ballot, Long> {
}
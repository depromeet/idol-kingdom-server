package com.example.idolkingdom.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "vote")
data class Vote(
    @Id
    @GeneratedValue
    val id: Int,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    @OneToMany(mappedBy = "vote")
    val ballots: List<Ballot>
)

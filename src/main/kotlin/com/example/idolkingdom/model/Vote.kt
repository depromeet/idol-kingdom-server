package com.example.idolkingdom.model

import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "vote")
data class Vote(
    @Id
    @GeneratedValue
    val id: Int,
    val startDate: ZonedDateTime,
    val endDate: ZonedDateTime,
    @OneToMany(mappedBy = "vote")
    val ballots: List<Ballot>
)

package com.example.idolkingdom.model

import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "user")
data class User(
    val id: Int,
    val email: String,
    val password: String,
    val name: String,
    @ManyToMany
    @JoinTable(name = "user_schools")
    val schools: List<School>,
    @ManyToMany
    @JoinTable(name = "user_idols")
    val idols: List<IdolGroup>,
    @OneToMany(mappedBy = "user")
    val ballots: List<Ballot>,
    val createdAt: ZonedDateTime
)

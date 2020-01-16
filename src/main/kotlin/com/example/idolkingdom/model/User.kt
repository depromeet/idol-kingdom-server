package com.example.idolkingdom.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "user")
data class User(
    @Id
    @GeneratedValue
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
    val createdAt: LocalDateTime
)

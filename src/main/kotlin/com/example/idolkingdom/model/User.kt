package com.example.idolkingdom.model

import lombok.AccessLevel
import lombok.NoArgsConstructor
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
data class User(
    @Id
    @GeneratedValue
    val id: Int,
    val email: String,
    val password: String,
    val name: String,
    val nickName: String,
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

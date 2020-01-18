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
    val id: Int? = null,
    val email: String,
    val password: String,
    val nickName: String,
    @ManyToMany
    @JoinColumn(name = "school_id")
    val school: List<School> = listOf(),
    @ManyToMany
    @JoinTable(name = "user_idols")
    val idols: List<IdolGroup>? = listOf(),
    @OneToMany(mappedBy = "user")
    val ballots: List<Ballot>? = listOf(),
    val createdAt: LocalDateTime = LocalDateTime.now()
)

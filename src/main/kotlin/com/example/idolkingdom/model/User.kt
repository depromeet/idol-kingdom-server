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
    val id: Long? = null,
    val email: String,
    val password: String,
    val nickName: String,
    @ManyToMany
    @JoinColumn(name = "school_id")
    var schools: List<School> = listOf(),
    @ManyToMany
    @JoinTable(name = "user_idols")
    var idols: List<IdolGroup>? = listOf(),
    @OneToMany(mappedBy = "user")
    var ballots: List<Ballot>? = listOf(),
    val createdAt: LocalDateTime = LocalDateTime.now()
)

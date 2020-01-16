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
    val name: String,
    val nickName: String,

    @ManyToOne
    @JoinColumn(name = "school_id")
    val school: School? = null,

//    @ManyToMany
//    @JoinTable(name = "user_idols")
//    val idols: List<IdolGroup>? = null,
//
//    @OneToMany(mappedBy = "user")
//    val ballots: List<Ballot>? = null,
    val createdAt: LocalDateTime? = null
)

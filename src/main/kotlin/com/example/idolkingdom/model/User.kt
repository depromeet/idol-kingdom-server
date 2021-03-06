package com.example.idolkingdom.model

import lombok.AccessLevel
import lombok.NoArgsConstructor
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
@EntityListeners(AuditingEntityListener::class)
data class User(
    @Id
    @GeneratedValue
    val id: Long? = null,
    var email: String,
    var password: String,
    var nickName: String,
    var profileImage: String? = null,
    @ManyToMany
    @JoinColumn(name = "school_id")
    var schools: List<School> = listOf(),
    @ManyToMany
    @JoinTable(name = "user_idols")
    var idols: List<IdolGroup> = listOf(),
    @OneToMany(mappedBy = "user")
    var ballots: List<Ballot> = listOf(),
    var restBallotCount: Int = 0,
    var lastAttendantDate: LocalDateTime? = null,
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now()
)

package com.example.idolkingdom.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "ballot")
@EntityListeners(AuditingEntityListener::class)
data class Ballot(
    @Id
    @GeneratedValue
    val id: Long? = null,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
    @ManyToOne
    @JoinColumn(name = "idol_group_id")
    val idol: IdolGroup,
    @ManyToOne
    @JoinColumn(name = "vote_id")
    val vote: Vote,
    @CreatedDate
    val date: LocalDateTime = LocalDateTime.now()
)

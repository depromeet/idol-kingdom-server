package com.example.idolkingdom.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "comment")
data class Comment(
    @Id
    @GeneratedValue
    val id: Int,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val writer: User,
    val content: String,
    @ManyToOne
    @JoinColumn(name = "idol_group_id")
    val idolGroup: IdolGroup,
    @ManyToOne
    @JoinColumn(name = "school_id")
    val school: School,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

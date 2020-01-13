package com.example.idolkingdom.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "ballot")
data class Ballot(
    val id: Int,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
    @ManyToOne
    @JoinColumn(name = "idol_group_id")
    val target: IdolGroup,
    @ManyToOne
    @JoinColumn(name = "vote_id")
    val vote: Vote,
    val date: Date
)

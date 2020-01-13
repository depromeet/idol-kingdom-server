package com.example.idolkingdom.model

import lombok.AccessLevel
import lombok.NoArgsConstructor
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
data class User(
        @Id @GeneratedValue
        val id: Int? = null,

        val email: String,
        val password: String,
        val name: String,
        val nickName: String,

        @ManyToOne
        @JoinColumn(name = "school_id")
        val school: School? = null,

        @ManyToOne
        @JoinColumn(name = "idol_group_id")
        val myIdol: IdolGroup? = null,

        val lovedGroups: List<IdolGroup>? = null,
        val votedList: List<Vote>? = null,
        val createdAt: LocalDateTime? = null
)
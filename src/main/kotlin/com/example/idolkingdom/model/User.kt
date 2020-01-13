package com.example.idolkingdom.model

import lombok.AccessLevel
import lombok.NoArgsConstructor
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
data class User(
        @Id @GeneratedValue
        val id: Int? = null,

        val email: String,
        val password: String,
        val name: String,

        val school: School? = null,
        val lovedGroups: List<IdolGroup>? = null,
        val votedList: List<Vote>? = null,
        val createdAt: LocalDateTime? = null
) {}
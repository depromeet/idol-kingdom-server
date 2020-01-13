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
    val id: Int,

    val email: String,
    val password: String,
    val name: String,

    val school: School,
    val lovedGroups: List<IdolGroup>,
    val votedList: List<Vote>,
    val createdAt: LocalDateTime
) {}
package com.example.idolkingdom.dto

import com.example.idolkingdom.model.Ballot
import com.example.idolkingdom.model.IdolGroup
import com.example.idolkingdom.model.School
import com.example.idolkingdom.model.User
import java.time.LocalDateTime

class UserDto(
    val email: String,
    val password: String,
    val name: String,
    val nickName: String,
    val schools: List<School>,
    val idols: List<IdolGroup>,
    val ballots: List<Ballot>,
    val createdAt: LocalDateTime
) {
    fun toUser(): User {
        return User(
            id = 0, // 빌드 성공을 위해 우선 넣어두었습니다.
            email = this.email,
            password = this.password,
            name = this.name,
            nickName = this.nickName,
            schools = this.schools,
            idols = this.idols,
            ballots =  this.ballots,
            createdAt = this.createdAt
        )
    }
}
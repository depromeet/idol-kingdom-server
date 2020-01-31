package com.example.idolkingdom.dto

import java.time.LocalDateTime

class BallotResponseDto(
    val id: Long,
    val user: Long,
    val idol: Long,
    val vote: Long,
    val date: LocalDateTime
)
package com.example.idolkingdom.dto

import java.time.LocalDateTime

class VoteRequestDto(
    val title: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime
)
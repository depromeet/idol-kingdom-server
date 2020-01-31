package com.example.idolkingdom.dto

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

class VoteRequestDto(
    val title: String,
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    val startDate: LocalDateTime,
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    val endDate: LocalDateTime
)
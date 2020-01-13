package com.example.idolkingdom.model

import java.time.ZonedDateTime

data class Vote(
    val id: Int,
    val startDate: ZonedDateTime,
    val endDate: ZonedDateTime,
    val target: IdolGroup,
    val votingList: List<Voting>
)

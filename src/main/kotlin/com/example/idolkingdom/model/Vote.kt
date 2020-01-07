package com.example.idolkingdom.model

import java.util.*

data class Vote(
    val id: Int,
    val startDate: Date,
    val endDate: Date,
    val target: IdolGroup,
    val votingList: List<Voting>
) {
    data class Voting(
        val id: Int,
        val user: User,
        val date: Date
    )
}
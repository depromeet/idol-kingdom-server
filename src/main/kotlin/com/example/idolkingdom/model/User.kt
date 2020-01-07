package com.example.idolkingdom.model

import java.util.*

data class User(
    val id: Int,
    val name: String,
    val school: School,
    val account: Account,
    val lovedGroups: List<IdolGroup>,
    val votedList: List<Vote>,
    val createdAt: Date
) {

    data class Account(
        val email: String,
        val password: String
//        val kakaoId: String,
//        val facebookId: String
    )
}
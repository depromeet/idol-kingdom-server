package com.example.idolkingdom.dao

data class IdolDao(
    val name: String,
    val image: String,
    val info: Info,
    val groups: List<String>
) {
    data class Info(
        val bloodType: String,
        val entertainmentName: String,
        val graduation: String,
        val hometown: String,
        val birth: String
    )
}
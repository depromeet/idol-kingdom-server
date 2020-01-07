package com.example.idolkingdom.model

import java.util.*


data class Comment(
    val id: Int,
    val writer: User,
    val content: String,
    val target: Target,
    val createdAt: Date,
    val updatedAt: Date,
    val likes: List<User>
) {
    data class Target(
        val idolGroup: IdolGroup,
        val school: School
    )
}
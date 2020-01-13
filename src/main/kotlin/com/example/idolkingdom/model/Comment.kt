package com.example.idolkingdom.model

import java.time.ZonedDateTime


data class Comment(
    val id: Int,
    val writer: User,
    val content: String,
    val target: CommentTarget,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
    val likes: List<User>
)

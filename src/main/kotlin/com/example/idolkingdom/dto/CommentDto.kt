package com.example.idolkingdom.dto

import com.example.idolkingdom.model.Comment
import java.time.LocalDateTime

data class CommentDto(
    val id: Long? = null,
    val writer: UserDto,
    val content: String,
    val idol: IdolDto,
    val school: SchoolResponseDto,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

fun Comment.toDto() = CommentDto(
    id,
    writer.toOutSiderDto(),
    content,
    idolGroup.toDto(emptyList()),
    SchoolResponseDto.of(school),
    createdAt,
    updatedAt
)

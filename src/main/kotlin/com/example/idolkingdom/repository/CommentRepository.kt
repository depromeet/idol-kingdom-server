package com.example.idolkingdom.repository

import com.example.idolkingdom.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findBySchoolIdIn(schoolIds: List<Long>): List<Comment>
    fun findByIdolGroupIdIn(schoolIds: List<Long>): List<Comment>
    fun findByWriterId(writerId: Long): List<Comment>
}
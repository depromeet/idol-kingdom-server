package com.example.idolkingdom.service


import com.example.idolkingdom.dto.CommentDto

interface CommentService {
    fun getAll(): List<CommentDto>
    fun getInSchools(schoolIds: List<Long>): List<CommentDto>
    fun getInIdols(idolIds: List<Long>): List<CommentDto>
    fun getByUserId(userId: Long): List<CommentDto>
    fun writeCommend(writerId: Long, content: String, idolId: Long, schoolId: Long): CommentDto
    fun deleteComment(writerId: Long, commendId: Long)
}
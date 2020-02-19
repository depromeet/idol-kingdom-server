package com.example.idolkingdom.service.impl

import com.example.idolkingdom.dto.CommentDto
import com.example.idolkingdom.dto.toDto
import com.example.idolkingdom.model.Comment
import com.example.idolkingdom.repository.CommentRepository
import com.example.idolkingdom.repository.IdolGroupRepository
import com.example.idolkingdom.repository.SchoolRepository
import com.example.idolkingdom.repository.UserRepository
import com.example.idolkingdom.service.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

@Service
class CommentServiceImpl(
    @Autowired private val commentRepository: CommentRepository,
    @Autowired private val idolGroupRepository: IdolGroupRepository,
    @Autowired private val schoolRepository: SchoolRepository,
    @Autowired private val userRepository: UserRepository
) : CommentService {
    override fun getAll(): List<CommentDto> = commentRepository.findAll().map { it.toDto() }

    override fun getInSchools(schoolIds: List<Long>): List<CommentDto> = commentRepository.findBySchoolIdIn(schoolIds)
        .map { it.toDto() }

    override fun getInIdols(idolIds: List<Long>): List<CommentDto> = commentRepository.findByIdolGroupIdIn(idolIds)
        .map { it.toDto() }

    override fun getByUserId(userId: Long): List<CommentDto> = commentRepository.findByWriterId(userId).map { it.toDto() }

    override fun writeCommend(writerId: Long, content: String, idolId: Long, schoolId: Long): CommentDto {
        val user = userRepository.getOne(writerId)
        val idol = idolGroupRepository.getOne(idolId)
        val school = schoolRepository.getOne(schoolId)
        val comment = Comment(
            writer = user,
            content = content,
            idolGroup = idol,
            school = school
        )
        return commentRepository.save(comment).toDto()
    }

    override fun deleteComment(writerId: Long, commendId: Long) {
        val comment = commentRepository.getOne(commendId)
        if (comment.writer.id == writerId)
            commentRepository.delete(comment)
        else throw IllegalStateException("permission denied")
    }


}
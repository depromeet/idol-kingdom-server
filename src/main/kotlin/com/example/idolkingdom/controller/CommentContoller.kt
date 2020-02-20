package com.example.idolkingdom.controller;

import com.example.idolkingdom.controller.request.CommentRequest
import com.example.idolkingdom.controller.response.CommentResponse
import com.example.idolkingdom.controller.response.IdolGroupResponse
import com.example.idolkingdom.service.CommentService
import com.example.idolkingdom.service.IdolService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class CommentContoller(
    @Autowired private val commentService: CommentService
) {

    @GetMapping("/comment")
    fun get(
        @RequestParam(value = "schoolIds") schoolIds: List<Long>?,
        @RequestParam(value = "idolIds") idolIds: List<Long>?,
        @RequestParam userId: Long?
    ): ResponseEntity<CommentResponse> = ResponseEntity.status(HttpStatus.OK)
        .body(
            CommentResponse(
                when {
                    schoolIds?.isNotEmpty() == true -> commentService.getInSchools(schoolIds)
                    idolIds?.isNotEmpty() == true -> commentService.getInIdols(idolIds)
                    userId != null -> commentService.getByUserId(userId)
                    else -> commentService.getAll()
                }
            )
        )

    @PostMapping("/comment")
    fun post(@RequestAttribute("id") id: Long, @RequestBody request: CommentRequest): ResponseEntity<CommentResponse> =
        ResponseEntity.status(HttpStatus.OK)
            .body(
                CommentResponse(
                    listOf(commentService.writeCommend(id, request.content, request.idolId, request.schoolId))
                )
            )

    @DeleteMapping("/comment")
    fun post(@RequestAttribute("id") id: Long, @RequestParam commentId: Long): ResponseEntity<Any?> =
        ResponseEntity(commentService.deleteComment(id,commentId),HttpStatus.OK)

}

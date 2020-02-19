package com.example.idolkingdom.controller.request

data class CommentRequest(
    val content : String,
    val idolId : Long,
    val schoolId : Long
)
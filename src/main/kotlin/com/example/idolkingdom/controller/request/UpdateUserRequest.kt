package com.example.idolkingdom.controller.request


data class UpdateUserRequest(
    val data: Data
) {
    data class Data(
        val email: String?,
        val password: String?,
        val nickName: String?,
        var schools: List<Long>?,
        var idols: List<Long>?
    )
}
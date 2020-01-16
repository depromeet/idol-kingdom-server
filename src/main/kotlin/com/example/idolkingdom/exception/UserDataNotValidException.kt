package com.example.idolkingdom.exception

class UserDataNotValidException : RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}
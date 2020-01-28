package com.example.idolkingdom.exception

class WrongParameterAcceptedException : RuntimeException {

    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)
}
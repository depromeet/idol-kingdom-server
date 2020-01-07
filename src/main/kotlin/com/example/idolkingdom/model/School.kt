package com.example.idolkingdom.model

data class School(
    val id: String,
    val name: String,
    val location: Location,
    val level: Level,
    val users: List<User>
) {

    enum class Level {
        ELEMENT, MIDDLE, HIGH, UNIVERSAL
    }

    //fun getLastLank() Todo : Users로 랭크 뽑아야 할 것 같은데 헬이네요... 의견 부탁드립니다.
}
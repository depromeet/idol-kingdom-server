package com.example.idolkingdom.model

import javax.persistence.*

@Entity
data class School(
        @Id @GeneratedValue
        val id: String? = null,
        val name: String,
        val location: Location,
        val level: Level,
        @OneToMany(mappedBy = "school", cascade = [CascadeType.ALL])
        val users: List<User>
) {

    enum class Level {
        ELEMENT, MIDDLE, HIGH, UNIVERSAL
    }

    //fun getLastLank() Todo : Users로 랭크 뽑아야 할 것 같은데 헬이네요... 의견 부탁드립니다.
}
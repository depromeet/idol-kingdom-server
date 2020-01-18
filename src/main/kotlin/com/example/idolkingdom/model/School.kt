package com.example.idolkingdom.model

import javax.persistence.*

@Entity
@Table(name = "school", indexes = arrayOf(Index(columnList = "name")))
data class School(
    @Id
    @GeneratedValue
    val id: Long? = null,
    val name: String,
    val address: String,
    @Embedded
    val location: Location,
    @Enumerated(EnumType.STRING)
    val level: Level,
    @ManyToMany(mappedBy = "school", cascade = [CascadeType.ALL])
    val users: List<User> = listOf()

) {
    enum class Level {
        ELEMENT, MIDDLE, HIGH, UNIVERSAL
    }
}

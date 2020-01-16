package com.example.idolkingdom.model

import javax.persistence.*

@Entity
@Table(name = "school")
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
    @ManyToMany(mappedBy = "schools")
    val users: List<User>? = null
) {
    enum class Level {
        ELEMENT, MIDDLE, HIGH, UNIVERSAL
    }
}

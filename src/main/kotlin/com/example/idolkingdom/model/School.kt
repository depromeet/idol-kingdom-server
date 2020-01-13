package com.example.idolkingdom.model

import javax.persistence.*

@Entity
@Table(name = "school")
data class School(
    val id: String,
    val name: String,
    val location: Location,
    @Enumerated(EnumType.ORDINAL)
    val level: Level,
    @ManyToMany(mappedBy = "schools")
    val users: List<User>
) {
    enum class Level {
        ELEMENT, MIDDLE, HIGH, UNIVERSAL
    }
}

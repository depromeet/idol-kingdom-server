package com.example.idolkingdom.model

import javax.persistence.*

@Entity
@Table(name = "school")
data class School(
    @Id
    @GeneratedValue
    val id: String,
    val name: String,
    @Embedded
    val location: Location,
    @Enumerated(EnumType.ORDINAL)
    val level: Level,
    @OneToMany(mappedBy = "school", cascade = [CascadeType.ALL])
    val users: List<User>
) {
    enum class Level {
        ELEMENT, MIDDLE, HIGH, UNIVERSAL
    }
}

package com.example.idolkingdom.model

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "idol")
data class Idol(
    @Id
    @GeneratedValue
    val id: Long? = null,
    val name: String,
    @Enumerated(EnumType.ORDINAL)
    val bloodType: BloodType,
    @ManyToMany(mappedBy = "members")
    val groups: List<IdolGroup> = listOf(),
    @ManyToOne
    @JoinColumn(name = "entertainment_id")
    val entertainment: Entertainment?,
    val graduation: String,
    val dateOfBirth: LocalDate?,
    val hometown: String,
    @OneToMany
    @JoinColumn(name = "idol_id")
    val images: List<Image> = listOf()
) {
    enum class BloodType(val value: String) {
        A("A"), B("B"), AB("AB"), O("O"), UNKNOWN("");

        companion object {
            fun get(value: String) = BloodType.values().find { it.value == value } ?: UNKNOWN
        }
    }
}

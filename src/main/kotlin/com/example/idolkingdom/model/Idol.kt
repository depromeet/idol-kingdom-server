package com.example.idolkingdom.model

import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "idol")
data class Idol(
    @Id
    @GeneratedValue
    val id: Int,
    val name: String,
    val description: String,
    @Enumerated(EnumType.ORDINAL)
    val bloodType: BloodType,
    @ManyToMany(mappedBy = "members")
    val groups: List<IdolGroup>,
    @ManyToOne
    @JoinColumn(name = "entertainment_id")
    val entertainment: Entertainment,
    val graduation: String,
    val dateOfBirth: ZonedDateTime,
    val hometown: String,
    val images: List<String>
) {
    enum class BloodType {
        A, B, AB, O;
    }
}

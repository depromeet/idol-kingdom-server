package com.example.idolkingdom.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class IdolGroup(
        @Id @GeneratedValue
        val id: String? = null,
        val name: String,
        val description: String,
        val members: List<Idol>,
        val images: List<String>,
        val votes: List<Vote>
)
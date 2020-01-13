package com.example.idolkingdom.model

import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "entertainment")
data class Entertainment(
    val id: Int,
    val name: String,
    @OneToMany(mappedBy = "entertainment")
    val idols : List<Idol>
)

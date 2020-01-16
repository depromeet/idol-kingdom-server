package com.example.idolkingdom.model

import javax.persistence.*

@Entity
@Table(name = "entertainment")
data class Entertainment(
    @Id
    @GeneratedValue
    val id: Int,
    val name: String,
    @OneToMany(mappedBy = "entertainment")
    val idols : List<Idol>
)
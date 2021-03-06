package com.example.idolkingdom.model

import javax.persistence.*

@Entity
@Table(name = "entertainment")
data class Entertainment(
    @Id
    @GeneratedValue
    val id: Long? = null,
    val name: String
){
    @OneToMany(mappedBy = "entertainment")
    var idols: List<Idol> = listOf()
}

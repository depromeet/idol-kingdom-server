package com.example.idolkingdom.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "image")
data class Image(
    @Id
    @GeneratedValue
    val id: Int,
    val url: String
)
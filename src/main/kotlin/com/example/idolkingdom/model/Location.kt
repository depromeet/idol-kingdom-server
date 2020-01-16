package com.example.idolkingdom.model

import javax.persistence.Embeddable

@Embeddable
data class Location(
    val x: Float,
    val y: Float,
    val z: Float
)

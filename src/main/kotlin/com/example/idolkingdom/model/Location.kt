package com.example.idolkingdom.model

import javax.persistence.Embeddable

@Embeddable
data class Location(
        val latitude: Float,
        val longitude: Float
)

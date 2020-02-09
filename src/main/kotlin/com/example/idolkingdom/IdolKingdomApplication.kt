package com.example.idolkingdom

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder


@SpringBootApplication
class IdolKingdomApplication

const val APPLICATION_LOCATIONS = ("spring.config.location="
    + "classpath:application.yml,"
    + "classpath:aws.yml")


fun main(args: Array<String>) {
    SpringApplicationBuilder(IdolKingdomApplication::class.java)
        .properties(APPLICATION_LOCATIONS)
        .run(*args)
}

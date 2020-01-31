package com.example.idolkingdom.util

import com.example.idolkingdom.dao.SchoolDao
import com.example.idolkingdom.model.Location
import com.example.idolkingdom.model.School
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import java.io.BufferedReader
import java.util.stream.Stream

object SchoolJsonParser {

    fun parse(path: String): Stream<School> {

        val mapper = jacksonObjectMapper()
        val stream = ClassPathResource(path).inputStream
        val reader = BufferedReader(stream.reader())
        val content = StringBuilder()
        try {
            var line = reader.readLine()
            while (line != null) {
                content.append(line)
                line = reader.readLine()
            }
        } finally {
            reader.close()
        }

        val genres = mapper.readValue<List<SchoolDao>>(content.toString())
        return genres.stream().map { s ->
            val level: School.Level = when {
                s.name.contains("중학교") -> School.Level.MIDDLE
                s.name.contains("고등학교") -> School.Level.HIGH
                else -> School.Level.ELEMENT
            }
            School(
                name = s.name,
                location = Location(s.latitude, s.longitude),
                address = s.address,
                level = level
            )
        }
    }
}
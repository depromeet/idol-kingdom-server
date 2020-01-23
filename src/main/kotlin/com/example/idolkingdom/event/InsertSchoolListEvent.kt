package com.example.idolkingdom.event


import com.example.idolkingdom.model.Location
import com.example.idolkingdom.model.School
import com.example.idolkingdom.repository.SchoolRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.io.BufferedReader


@Component
class InsertSchoolListEvent(@Autowired private val schoolRepository: SchoolRepository)
    : ApplicationListener<ApplicationReadyEvent> {

    val mapper = jacksonObjectMapper()

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        val stream = ClassPathResource("static/schoolList.json").inputStream
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

        genres.stream().map { s ->
            val level: School.Level
            if (s.name.contains("중학교")) {
                level = School.Level.MIDDLE
            } else if (s.name.contains("고등학교")) {
                level = School.Level.HIGH
            } else {
                level = School.Level.ELEMENT
            }

            School(
                name = s.name,
                location = Location(s.latitude, s.longitude),
                address = s.address,
                level = level
            )

        }.forEach { s -> schoolRepository.save(s) }

    }


}
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
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


@Component
class InsertSchoolListEvent(@Autowired private val schoolRepository: SchoolRepository)
    : ApplicationListener<ApplicationReadyEvent> {

    val mapper = jacksonObjectMapper()

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        val resource = ClassPathResource("static/schoolList.json")
        val path: Path = Paths.get(resource.uri)
        val content: String = Files.readAllLines(path)[0]
        val genres = mapper.readValue<List<SchoolDao>>(content)

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
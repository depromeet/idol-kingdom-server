package com.example.idolkingdom.event


import com.example.idolkingdom.repository.SchoolRepository
import com.example.idolkingdom.util.Resource
import com.example.idolkingdom.util.SchoolJsonParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component


@Component
class InsertSchoolListEvent(@Autowired private val schoolRepository: SchoolRepository)
    : ApplicationListener<ApplicationReadyEvent> {

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        SchoolJsonParser.parse(Resource.PATH_SCHOOL_JSON)
            .forEach { s -> schoolRepository.save(s) }
    }
}

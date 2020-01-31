package com.example.idolkingdom.service

import com.example.idolkingdom.dto.LocationDto
import com.example.idolkingdom.service.impl.SchoolServiceImpl
import com.example.idolkingdom.util.Resource
import com.example.idolkingdom.util.SchoolJsonParser
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import kotlin.streams.toList

@Suppress("NonAsciiCharacters")
@Transactional
@RunWith(SpringRunner::class)
@SpringBootTest
class SchoolServiceTest {

    @Autowired
    private lateinit var servcie: SchoolServiceImpl

    private val schools by lazy {
        SchoolJsonParser.parse(Resource.PATH_SCHOOL_JSON)
    }

    @Test
    fun getAll() {
        Assert.assertEquals(
            schools.toList().size,
            servcie.getAll().size
        )
    }

    @Test
    fun get() {
        Assert.assertEquals(
            schools.filter { it.name == "대신고등학교" }.count().toInt(),
            servcie.get("대신고등학교").size
        )
    }

    @Test
    fun search() {
        Assert.assertEquals(
            schools.filter { it.name.contains("풍") }.count().toInt(),
            servcie.search("풍", null).size
        )
    }

    @Test
    fun searchLocation() {
        val startLocation = LocationDto(37.582484f, 126.954486f)
        val endLocation = LocationDto(37.569083f, 126.984183f)
        val expected = schools.filter {
            it.location.latitude < startLocation.latitude &&
                endLocation.latitude < it.location.latitude &&
                it.location.longitude < endLocation.longitude &&
                startLocation.longitude < it.location.longitude
        }
        val target = servcie.search(
            startLocation,
            endLocation,
            2
        )
        Assert.assertEquals(
            expected.count().toInt(),
            target.size
        )
    }
}

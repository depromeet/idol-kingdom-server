package com.example.idolkingdom.service

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
            servcie.search("풍").size
        )
    }
}

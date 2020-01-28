package com.example.idolkingdom.service

import com.example.idolkingdom.service.impl.IdolServiceImpl
import com.example.idolkingdom.service.impl.SchoolServiceImpl
import com.example.idolkingdom.util.IdolJsonParser
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
class IdolServiceTest {

    @Autowired
    private lateinit var servcie: IdolServiceImpl

    private val idols by lazy {
        IdolJsonParser.parse(Resource.PATH_IDOL_JSON)
    }

    @Test
    fun getAll() {
        Assert.assertEquals(
            idols.flatMap { it.groups }.distinct().size,
            servcie.getAll().size
        )
    }

    @Test
    fun search() {
        Assert.assertEquals(
            idols.flatMap { it.groups }.distinct().filter { it.contains("방") }.size,
            servcie.search("방").size
        )
    }
}

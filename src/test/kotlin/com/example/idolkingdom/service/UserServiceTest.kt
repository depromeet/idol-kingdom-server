package com.example.idolkingdom.service

import com.example.idolkingdom.model.User
import com.example.idolkingdom.repository.UserRepository
import com.example.idolkingdom.service.Impl.JwtTokenUtil
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional


@SpringBootTest
@ExtendWith(SpringExtension::class)
@Transactional
@ActiveProfiles("test")
class UserServiceTest(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val jwtTokenUtil: JwtTokenUtil) {


    fun getUser(): User {
        return User(
            id = 1,
            name = "depromeet",
            password = "ddd",
            nickName = "depro",
            email = "depromeet@depromeet.com"

        )
    }

}
//package com.example.idolkingdom.service
//
//import com.example.idolkingdom.dto.UserDto
//import com.example.idolkingdom.model.User
//import com.example.idolkingdom.repository.UserRepository
//import javassist.NotFoundException
//import org.hamcrest.core.Is.`is`
//import org.junit.Assert.assertThat
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.test.context.ActiveProfiles
//import org.springframework.test.context.junit.jupiter.SpringExtension
//import org.springframework.transaction.annotation.Transactional
//
//
//@SpringBootTest
//@ExtendWith(SpringExtension::class)
//@Transactional
//@ActiveProfiles("test")
//class UserServiceTest(
//    @Autowired private val userService: UserService,
//    @Autowired private val userRepository: UserRepository) {
//
//
//    @Test
//    fun signUp() {
//        //given
//        val userDto: UserDto = getUserDto()
//        userService.createUser(userDto)
//
//        //when /then
//        val user: User = userRepository.findByEmail(userDto.email) ?: throw NotFoundException("");
//        assertThat(user.name, `is`(userDto.name))
//    }
//
//
//    private fun getUser(): User {
//        return User(
//            id = 1,
//            name = "depromeet",
//            password = "ddd",
//            nickName = "depro",
//            email = "depromeet@depromeet.com"
//        )
//    }
//
//    private fun getUserDto(): UserDto {
//        return UserDto(
//            name = "depromeet",
//            password = "ddd",
//            nickName = "depro",
//            email = "depromeet@depromeet.com"
//        )
//    }
//
//}
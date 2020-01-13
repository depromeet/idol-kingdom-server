package com.example.idolkingdom.service.Impl

import com.example.idolkingdom.dto.UserDto
import com.example.idolkingdom.exception.UserDataNotValidException
import com.example.idolkingdom.model.User
import com.example.idolkingdom.repository.UserRepository
import com.example.idolkingdom.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(@Autowired private val userRepository: UserRepository) : UserService {

    override fun createUser(userDto: UserDto) {
        validCreateUser(userDto)
        userRepository.save(userDto.toUser())
    }

    private fun validCreateUser(userDto: UserDto) {
        val user: User? = userRepository.findByEmail(userDto.email)
        if (user != null) {
            throw UserDataNotValidException("already user exist")
        }
    }
}
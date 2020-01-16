package com.example.idolkingdom.service.Impl

import com.example.idolkingdom.dto.LoginRequestDto
import com.example.idolkingdom.dto.LoginResponseDto
import com.example.idolkingdom.dto.UserDto
import com.example.idolkingdom.exception.UserDataNotValidException
import com.example.idolkingdom.exception.UserNotFoundException
import com.example.idolkingdom.model.User
import com.example.idolkingdom.repository.UserRepository
import com.example.idolkingdom.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(@Autowired private val userRepository: UserRepository,
                      @Autowired private val jwtTokenUtil: JwtTokenUtil,
                      @Autowired private val passwordEncoder: PasswordEncoder) : UserService {

    override fun createUser(userDto: UserDto): User {
        validCreateUser(userDto)
        userDto.password = passwordEncoder.encode(userDto.password)
        return userRepository.save(userDto.toUser())
    }

    override fun login(loginRequestDto: LoginRequestDto): LoginResponseDto {
        val user: User = validLogin(loginRequestDto)
        val token = jwtTokenUtil.generateToken(user)
        return LoginResponseDto(token)
    }

    override fun updateUser(userDto: UserDto): User {
        return userRepository.save(userDto.toUser())
    }

    private fun validCreateUser(userDto: UserDto) {
        val user: User? = userRepository.findByEmail(userDto.email)
        if (user != null) {
            throw UserDataNotValidException("already user exist")
        }
    }

    private fun validLogin(loginRequestDto: LoginRequestDto): User {
        val user: User = userRepository.findByEmail(loginRequestDto.email)
            ?: throw UserNotFoundException("..")
        if (isCorrectPassword(loginRequestDto.password, user)) {
            throw UserDataNotValidException("")
        }
        return user
    }

    private fun isCorrectPassword(password: String, user: User): Boolean {
        return passwordEncoder.matches(user.password, password)
    }
}
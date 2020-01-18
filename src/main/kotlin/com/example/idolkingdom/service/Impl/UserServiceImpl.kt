package com.example.idolkingdom.service.impl

import com.example.idolkingdom.dto.EmailDto
import com.example.idolkingdom.dto.LoginRequestDto
import com.example.idolkingdom.dto.LoginResponseDto
import com.example.idolkingdom.dto.UserDto
import com.example.idolkingdom.exception.UserDataNotValidException
import com.example.idolkingdom.exception.UserNotFoundException
import com.example.idolkingdom.model.User
import com.example.idolkingdom.repository.IdolGroupRepository
import com.example.idolkingdom.repository.SchoolRepository
import com.example.idolkingdom.repository.UserRepository
import com.example.idolkingdom.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(@Autowired private val userRepository: UserRepository,
                      @Autowired private val schoolRepository: SchoolRepository,
                      @Autowired private val idolRepository: IdolGroupRepository,
                      @Autowired private val jwtTokenUtil: JwtTokenUtil,
                      @Autowired private val passwordEncoder: PasswordEncoder) : UserService {

    override fun createUser(dto: UserDto): LoginResponseDto {
        validCreateUser(dto)
        val user = userRepository.save(
            User(
                email = dto.email,
                nickName = dto.nickName,
                password = passwordEncoder.encode(dto.password),
                school = listOf(schoolRepository.getOne(dto.schoolId)),
                idols = listOf(idolRepository.getOne(dto.idolId))
            )
        )
        val token = jwtTokenUtil.generateToken(user)
        return LoginResponseDto(token)
    }

    override fun validateEmail(email: EmailDto): Boolean {
        return userRepository.findByEmail(email.email) == null
    }

    override fun login(loginRequestDto: LoginRequestDto): LoginResponseDto {
        val user: User = validLogin(loginRequestDto)
        val token = jwtTokenUtil.generateToken(user)
        return LoginResponseDto(token)
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
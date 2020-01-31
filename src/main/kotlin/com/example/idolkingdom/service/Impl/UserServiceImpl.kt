package com.example.idolkingdom.service.impl

import com.example.idolkingdom.dto.*
import com.example.idolkingdom.exception.DataNotFoundException
import com.example.idolkingdom.exception.UserDataNotValidException
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
                schools = listOf(schoolRepository.getOne(dto.schoolId)),
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

    override fun getUser(userId: Long): UserResponseDto {
        return userRepository.findById(userId).map { user ->
            UserResponseDto(
                email = user.email,
                nickName = user.nickName,
                schoolList = user.schools.map { s -> s.id },
                idolIdList = user.idols.map { idol -> idol.id },
                ballotList = user.ballots.map { b -> b.id }
            )
        }.get()
    }

    fun validCreateUser(userDto: UserDto) {
        val user: User? = userRepository.findByEmail(userDto.email)
        if (user != null) {
            throw UserDataNotValidException("already user exist")
        }
    }

    private fun validLogin(loginRequestDto: LoginRequestDto): User {
        val user: User = userRepository.findByEmail(loginRequestDto.email)
            ?: throw DataNotFoundException("..")
        if (isCorrectPassword(loginRequestDto.password, user)) {
            throw UserDataNotValidException("")
        }
        return user
    }

    private fun isCorrectPassword(password: String, user: User): Boolean {
        return passwordEncoder.matches(user.password, password)
    }
}
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
import java.lang.IllegalArgumentException
import java.time.LocalDateTime

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
                email = dto.email ?: throw IllegalArgumentException("email must not be null"),
                nickName = dto.nickName ?: throw IllegalArgumentException("nickname must not be null"),
                password = passwordEncoder.encode(dto.password),
                schools = listOf(
                    schoolRepository.getOne(
                        dto.schools?.firstOrNull() ?: throw IllegalArgumentException("schools must not be empty")
                    )
                ),
                idols = listOf(
                    idolRepository.getOne(
                        dto.idols?.firstOrNull() ?: throw IllegalArgumentException("idols must not be empty")
                    )
                )
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
                id = user.id,
                email = user.email,
                nickName = user.nickName,
                schoolList = user.schools.sortedByDescending { it.level.ordinal }.map { s -> s.id },
                idolIdList = user.idols.map { idol -> idol.id },
                ballotList = user.ballots.map { b -> b.id }
            )
        }.get()
    }

    override fun applyAttendance(id: Long): UserDto = userRepository.getOne(id).let { user ->
        val now = LocalDateTime.now()
        user.restBallotCount += user.lastAttendantDate?.let {
            if (it.dayOfMonth < now.dayOfMonth)
                1
            else 0
        } ?: 1
        user.lastAttendantDate = now
        return@let userRepository.save(user).toDto()
    }

    private fun validCreateUser(userDto: UserDto) {
        val user: User? = userRepository.findByEmail(
            userDto.email ?: throw IllegalArgumentException("email must not be null")
        )
        if (user != null) {
            throw UserDataNotValidException("already user exist")
        }
    }

    private fun validLogin(loginRequestDto: LoginRequestDto): User {
        val user: User = userRepository.findByEmail(loginRequestDto.email)
            ?: throw DataNotFoundException("..")
        if (isCorrectPassword(loginRequestDto.password, user).not()) {
            throw UserDataNotValidException("")
        }
        return user
    }

    private fun isCorrectPassword(password: String, user: User): Boolean {
        return passwordEncoder.matches(user.password, password)
    }
}
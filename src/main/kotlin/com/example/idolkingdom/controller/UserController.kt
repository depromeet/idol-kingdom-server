package com.example.idolkingdom.controller;

import com.example.idolkingdom.dto.*
import com.example.idolkingdom.exception.S3KeyDoesNotExistException
import com.example.idolkingdom.service.UserService
import com.example.idolkingdom.service.impl.S3Service
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartHttpServletRequest
import java.net.URI

@RestController
@RequestMapping("/api")
class UserController(private val userService: UserService, private val service: S3Service) {

    companion object {
        const val BASE_URI = "/v1/buckets"
        const val BUCKET_NAME = "/idol.kingdom"
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequestDto: LoginRequestDto): ResponseEntity<LoginResponseDto> =
        ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.login(loginRequestDto))

    @PostMapping("/users/validation")
    fun validateEmail(@RequestBody email: EmailDto): ResponseEntity<EmailDto> = ResponseEntity.status(
        if (userService.validateEmail(email))
            HttpStatus.OK
        else HttpStatus.FORBIDDEN
    ).body(email)

    @PostMapping("/users")
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<LoginResponseDto> =
        ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto))

    @GetMapping("/users")
    fun getUser(@RequestParam userId: Long): ResponseEntity<UserResponseDto> =
        ResponseEntity.status(HttpStatus.OK).body(userService.getUser(userId))

    @GetMapping("/me")
    fun getMe(@RequestAttribute("id") id: Long): ResponseEntity<UserResponseDto> =
        ResponseEntity.status(HttpStatus.CREATED).body(userService.getUser(id))


    @GetMapping("/users/profile")
    fun downloadProfileImage(@RequestAttribute("id") userId: Long): ResponseEntity<Resource> {
        return try {
            val s3Object = service.getObject(BUCKET_NAME, userId)
            ResponseEntity.ok(s3Object)
        } catch (exception: S3KeyDoesNotExistException) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/users/profile", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadProfileImage(@RequestAttribute("id") userId: Long, request: MultipartHttpServletRequest): ResponseEntity<Void> {
        val multipartFile = request.multiFileMap.toSingleValueMap().values.first()
        val objectKey = service.putObject(BUCKET_NAME, multipartFile.resource, userId)
        return ResponseEntity.created(URI("$BASE_URI/$BUCKET_NAME/objects/$objectKey")).build()
    }


}

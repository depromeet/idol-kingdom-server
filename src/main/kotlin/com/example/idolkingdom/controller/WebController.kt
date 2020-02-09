package com.example.idolkingdom.controller

import com.example.idolkingdom.exception.S3KeyDoesNotExistException
import com.example.idolkingdom.repository.S3Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class WebController(@Autowired private val service: S3Service) {
    @GetMapping("/hello")
    fun index(model: Model): String {
        return "hello"
    }

    @GetMapping("/{bucketName}/objects/{objectKey}")
    fun downloadS3Object(@PathVariable bucketName: String, @PathVariable objectKey: String): ResponseEntity<Resource> {
        return try {
            val s3Object = service.getObject(bucketName, objectKey)
            ResponseEntity.ok(s3Object)
        } catch (exception: S3KeyDoesNotExistException) {
            ResponseEntity.notFound().build()
        }
    }
}
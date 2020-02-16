package com.example.idolkingdom.service.impl

import com.example.idolkingdom.exception.DataNotFoundException
import com.example.idolkingdom.exception.S3KeyDoesNotExistException
import com.example.idolkingdom.repository.UserRepository
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import org.springframework.core.io.WritableResource
import org.springframework.stereotype.Service
import java.util.*

@Service
class S3Service(private val resourceLoader: ResourceLoader,
                private val userRepository: UserRepository) {

    @Throws(S3KeyDoesNotExistException::class)
    fun getObject(bucketName: String, userId: Long): Resource {
        val user = userRepository.findById(userId).orElseThrow { DataNotFoundException("user not found") }
        val resource = resourceLoader.getResource("s3:/$bucketName/$userId/${user.profileImage}")
        if (resource.exists()) {
            return resource
        }
        throw S3KeyDoesNotExistException(bucketName, userId)
    }

    fun putObject(bucketName: String, resource: Resource, userId: Long): String {
        val key = resource.filename ?: UUID.randomUUID().toString()
        val writableResource = this.resourceLoader.getResource("s3:/$bucketName/$userId/$key") as WritableResource
        writableResource.outputStream.use { fileOut ->
            resource.inputStream.copyTo(fileOut)
        }
        val user = userRepository.findById(userId).orElseThrow { DataNotFoundException("user not found") }
        user.profileImage = key
        userRepository.save(user)
        return key
    }

}
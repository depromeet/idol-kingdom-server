package com.example.idolkingdom.service.impl

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class S3Uploader(@Autowired private val amazonS3Client: AmazonS3Client) {

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String? = null

    @Throws(IOException::class)
    fun upload(multipartFile: MultipartFile, dirName: String): String {
        val uploadFile: File = convert(multipartFile)
            ?: throw IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다.")

        return upload(uploadFile, dirName)
    }

    private fun upload(uploadFile: File, dirName: String): String {
        val fileName = dirName + "/" + uploadFile.getName()
        val uploadImageUrl = putS3(uploadFile, fileName)
        removeNewFile(uploadFile)
        return uploadImageUrl
    }

    private fun putS3(uploadFile: File, fileName: String): String {
        amazonS3Client.putObject(PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead))
        return amazonS3Client.getUrl(bucket, fileName).toString()
    }

    private fun removeNewFile(targetFile: File) {
        targetFile.delete()
    }

    @Throws(IOException::class)
    private fun convert(file: MultipartFile): File? {
        val convertFile = File(file.originalFilename)
        if (convertFile.createNewFile()) {
            FileOutputStream(convertFile).use { fos -> fos.write(file.bytes) }
            return convertFile
        }
        return null
    }
}
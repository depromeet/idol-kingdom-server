package com.example.idolkingdom.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import java.net.URI

@Configuration
@ConfigurationProperties("s3properties")
class InfrastructureConfig {

    lateinit var endpointUrl: String
    lateinit var region: String
    lateinit var accessKey: String
    lateinit var secretKey: String

    @Bean
    fun s3Client(): S3Client {
        val credentials = AwsBasicCredentials.create(accessKey, secretKey)
        val credentialsProvider = StaticCredentialsProvider.create(credentials)
        return S3Client.builder()
            .credentialsProvider(credentialsProvider)
            .endpointOverride(URI.create(endpointUrl))
            .region(Region.of(region))
            .build()
    }
}
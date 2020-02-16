package com.example.idolkingdom.exception

class S3KeyDoesNotExistException(bucketName: String, key: Long)
    : RuntimeException("The key $key does not exist in bucket $bucketName")

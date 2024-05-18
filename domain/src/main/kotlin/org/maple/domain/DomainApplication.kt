package org.maple.domain

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class DomainApplication

fun main(args: Array<String>) {
    val credentials: AWSCredentials = ProfileCredentialsProvider().getCredentials()
    val accessKey: String = credentials.getAWSAccessKeyId()
    val secretKey: String = credentials.getAWSSecretKey()
    println("accessKey: $accessKey")
    println("secretKey: $secretKey")
    runApplication<DomainApplication>(*args)
}

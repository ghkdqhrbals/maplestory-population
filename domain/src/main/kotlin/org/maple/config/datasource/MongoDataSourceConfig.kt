package org.maple.config.datasource

import com.fooddash.orderHub.configuration.mongo.converter.MongoOffsetDateTimeReader
import com.fooddash.orderHub.configuration.mongo.converter.MongoOffsetDateTimeWriter
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.auditing.DateTimeProvider
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import java.time.OffsetDateTime
import java.util.*
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration

@Configuration
@EnableMongoRepositories
@EnableMongoAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
class MongoConfiguration(
    @Value("\${maplefusion.mongodb.uri}")
    private val mongoUri: String
) :AbstractMongoClientConfiguration() {


    @Bean
    fun mongoCustomConversions(): MongoCustomConversions {
        return MongoCustomConversions(
            listOf(
                MongoOffsetDateTimeWriter(),
                MongoOffsetDateTimeReader(),
            ),
        )
    }

    @Bean
    override fun mongoClient(): MongoClient {
        return MongoClients.create(mongoUri)
    }

    @Bean("auditingDateTimeProvider")
    fun auditingDateTimeProvider(): DateTimeProvider {
        return DateTimeProvider { Optional.of(OffsetDateTime.now()) }
    }

    override fun getDatabaseName(): String {
        return "maplefusion"
    }

}
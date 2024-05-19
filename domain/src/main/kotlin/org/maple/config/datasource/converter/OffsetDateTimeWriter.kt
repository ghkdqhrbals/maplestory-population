package com.fooddash.orderHub.configuration.mongo.converter

import org.springframework.core.convert.converter.Converter
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class MongoOffsetDateTimeWriter : Converter<OffsetDateTime, String> {
    override fun convert(source: OffsetDateTime): String {
        return source.toInstant()
            .atZone(ZoneOffset.UTC)
            .toOffsetDateTime()
            .toDateTimeString(
                pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                zoneId = ZoneId.of("UTC")
            )
    }
}

fun OffsetDateTime.toDateTimeString(pattern: String, zoneId: ZoneId): String {
    val formatter = DateTimeFormatter.ofPattern(pattern).withZone(zoneId)
    return this.format(formatter)
}
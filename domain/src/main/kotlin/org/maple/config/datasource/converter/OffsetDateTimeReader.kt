package com.fooddash.orderHub.configuration.mongo.converter

import org.springframework.core.convert.converter.Converter
import java.time.OffsetDateTime

class MongoOffsetDateTimeReader : Converter<String, OffsetDateTime> {
    override fun convert(source: String): OffsetDateTime {
        return OffsetDateTime.parse(source)
    }
}

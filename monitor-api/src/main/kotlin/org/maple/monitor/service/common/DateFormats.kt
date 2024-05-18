package org.maple.monitor.service.common

import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

object DateFormats {
    const val DATE_FORMAT = "yyyy-MM-dd"

    val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)


    fun refine(dateTime: OffsetDateTime): String {
        return dateTime.format(DateTimeFormatter.ofPattern(DATE_FORMAT))
    }

    fun refine(dateTime: LocalDateTime): String {
        return dateTime.format(DateTimeFormatter.ofPattern(DATE_FORMAT))
    }
}
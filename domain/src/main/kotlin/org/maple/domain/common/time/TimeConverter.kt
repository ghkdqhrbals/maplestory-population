package org.maple.domain.common.time

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

inline fun String.toLocalDateTime(): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return LocalDateTime.parse(this, formatter)
}
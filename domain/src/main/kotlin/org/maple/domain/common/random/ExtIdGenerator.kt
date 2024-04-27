package org.maple.domain.common.random

import java.nio.ByteBuffer
import java.util.*

fun generateExtId(): String {
    val uuid = UUID.randomUUID()
    return uuid.toString()
}
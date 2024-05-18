package org.maple.domain.rank.constant

import org.maple.domain.rank.constant.RankDefault.Companion.DEFAULT_INT
import org.maple.domain.rank.constant.RankDefault.Companion.DEFAULT_LONG
import org.maple.domain.rank.constant.RankDefault.Companion.DEFAULT_STRING

abstract class RankDefault {
    companion object {
        const val DEFAULT_STRING = ""
        const val DEFAULT_INT = -1
        const val DEFAULT_LONG = -1L
        const val DEFAULT_WORLD_NAME = "UNDEFINED"
    }
}

inline fun <reified T> getDefault(): T {
    // reified 키워드를 사용하여 타입 정보를 유지
    return when (T::class) {
        String::class -> DEFAULT_STRING as T
        Int::class -> DEFAULT_INT as T
        Long::class -> DEFAULT_LONG as T
        else -> throw IllegalArgumentException("Not supported type")
    }
}
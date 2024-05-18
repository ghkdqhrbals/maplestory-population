package org.maple.domain.common.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

object Jackson {
    private val mapper: ObjectMapper = JsonMapper.builder()
        .enable(SerializationFeature.INDENT_OUTPUT) // JSON 출력을 들여쓰기하여 가독성 향상
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // 날짜를 timestamp가 아닌 ISO 포맷으로 출력
        .addModule(JavaTimeModule()) // Java 8 시간 관련 데이터 유형을 지원하기 위한 모듈 추가
        .build()

    fun getMapper(isSnakeCase: Boolean): ObjectMapper = if (isSnakeCase) snakeCaseMapper else mapper

    private val snakeCaseMapper: ObjectMapper = JsonMapper.builder()
        .enable(SerializationFeature.INDENT_OUTPUT)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .addModule(JavaTimeModule())
        .build()
        .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
}
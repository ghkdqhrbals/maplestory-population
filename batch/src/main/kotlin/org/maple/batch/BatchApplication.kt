package org.maple.batch

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling
import kotlin.system.exitProcess

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
class BatchApplication
fun main(args: Array<String>) {
    runApplication<BatchApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
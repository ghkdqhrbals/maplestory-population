package org.maple.monitor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["org.maple"])
class MonitorApiApplication

fun main(args: Array<String>) {
    runApplication<MonitorApiApplication>(*args)
}

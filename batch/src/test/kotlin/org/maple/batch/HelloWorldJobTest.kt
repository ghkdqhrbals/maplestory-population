package org.maple.batch

import org.junit.jupiter.api.Test
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@SpringBatchTest
class HelloWorldJobTest {

    @Test
    fun test() {
        println("Hello, World!")
    }
}
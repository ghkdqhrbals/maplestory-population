package org.maple.config.http

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.time.Duration

@Configuration
class RestTemplateConfiguration {

    @Bean(name = ["commonRestTemplate"])
    fun commonRestTemplate(): RestTemplate {
        val requestFactory = SimpleClientHttpRequestFactory()
        requestFactory.setConnectTimeout(2 * 1000)
        requestFactory.setReadTimeout(3 * 1000)

        return RestTemplateBuilder()
            .setConnectTimeout(Duration.ofMillis(2 * 1000))
            .setReadTimeout(Duration.ofMillis(3 * 1000))
            .requestFactory { _ ->
                BufferingClientHttpRequestFactory(requestFactory)
            }
            .build()
    }
}
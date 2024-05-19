package org.maple.batch.job.population

import org.maple.domain.common.util.Jackson
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class MapleClient(
    @Value("\${keys.maple.api.host}")
    private val host: String,

    @Value("\${keys.maple.api.key}")
    private val key: String,
) {
    final val objectMapper = Jackson.getMapper(true)

    val client: WebClient = WebClient.builder()
        .baseUrl(host)
        .defaultHeaders {
            it.add("x-nxopen-api-key", key)
        }
        .codecs { configurer -> configurer.defaultCodecs().jackson2JsonDecoder(Jackson2JsonDecoder(objectMapper)) }
        .codecs { configurer -> configurer.defaultCodecs().jackson2JsonEncoder(Jackson2JsonEncoder(objectMapper)) }
        .build()

    fun getWebClient(): WebClient {
        return client
    }


}
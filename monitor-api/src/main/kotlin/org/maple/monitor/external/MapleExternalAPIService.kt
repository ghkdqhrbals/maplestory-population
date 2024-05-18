package org.maple.monitor.external

import org.maple.monitor.controller.rank.RankResponseWrapper
import org.maple.monitor.service.common.DateFormats
import org.springframework.stereotype.Component
import java.time.OffsetDateTime

@Component
class MapleExternalAPIService(
    private val mapleClient: MapleClient,
) {
    fun getSnapshotDate(beforeDays :Long): List<String> {
        return (0 until beforeDays).map {
            OffsetDateTime.now().minusDays(it).format(DateFormats.dateFormatter)
        }.sorted()
    }


    fun getCharacterInfo(date: OffsetDateTime): RankResponseWrapper? {
        return mapleClient.client.get().uri("/maplestory/v1/ranking/overall?date=${DateFormats.refine(date.toLocalDateTime())}")
            .attributes { mapOf("accept" to "application/json",) }
            .retrieve()
            .bodyToMono(RankResponseWrapper::class.java)
            .block()

    }
}
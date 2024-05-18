package org.maple.batch.job.population

import org.maple.domain.common.util.DateFormats
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


    /**
     * Page 1 부터 시작 ~ infinite
     *
     * webFlux 로 동시전송 및 동시에 받기
     * 1, 2, 3 ... 빈 값 받을 떄까지
     *
     * 1000 페이지 단위로 병렬실행
     */
    fun fetchPage(date: OffsetDateTime, page: Int): RankResponseWrapper? {
        return mapleClient.client.get().uri("/maplestory/v1/ranking/overall?date=${DateFormats.refine(date.toLocalDateTime())}" +
                "&page=$page")
            .attributes { mapOf("accept" to "application/json",) }
            .retrieve()
            .bodyToMono(RankResponseWrapper::class.java)
            .block()
    }
}
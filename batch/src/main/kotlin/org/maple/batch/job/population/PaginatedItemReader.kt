package org.maple.batch.job.population

import org.springframework.batch.item.ItemReader
import java.time.OffsetDateTime

class PaginatedItemReader(
    private val mapleExternalAPIService: MapleExternalAPIService,
    private val date: OffsetDateTime,
    private val pageSize: Int
) : ItemReader<RankResponseWrapper?> {

    private var currentPage = 1

    override fun read(): RankResponseWrapper? {
        val response = mapleExternalAPIService.fetchPage(date, currentPage)
        return if (response != null) {
            currentPage++
            response
        } else {
            null
        }
    }
}

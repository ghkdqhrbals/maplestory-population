package org.maple.monitor.service.rank

import org.maple.domain.rank.RankRepository
import org.springframework.stereotype.Service

@Service
class MaplefusionService(
    private val rankRepository: RankRepository,
) {
    fun lastSnapshotDate(): String {
        return "2021-08-01"
    }
}
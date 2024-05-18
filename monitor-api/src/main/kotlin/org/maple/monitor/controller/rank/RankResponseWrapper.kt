package org.maple.monitor.controller.rank

data class RankResponseWrapper(
    val ranking: List<RankResponse>? = mutableListOf()
)
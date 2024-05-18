package org.maple.batch.job.population

data class RankResponseWrapper(
    val ranking: List<RankResponse>? = mutableListOf()
)
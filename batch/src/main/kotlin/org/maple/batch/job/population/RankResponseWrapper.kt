package org.maple.batch.job.population

/**
 * RankResponseWrapper is a data class that wraps a list of RankResponse objects.
 */
data class RankResponseWrapper(
    val ranking: List<RankResponse>? = mutableListOf()
)
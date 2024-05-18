package org.maple.monitor.controller.rank

import org.maple.domain.common.time.toLocalDateTime
import org.maple.domain.rank.Rank
import org.maple.domain.rank.constant.getDefault


data class RankResponse(
    val worldName: String? = getDefault<String>(),
    val ranking: Int? = getDefault<Int>(),
    val characterName: String? = getDefault<String>(),
    val characterLevel: Int? = getDefault<Int>(),
    val characterExp: Long? = getDefault<Long>(),
    val className: String? = getDefault<String>(),
    val subClassName: String? = getDefault<String>(),
    val characterPopularity: Int? = getDefault<Int>(),
    val characterGuildname: String? = getDefault<String>(),
    val date: String? = getDefault<String>()
) {
    companion object {
        fun of(rank: Rank): RankResponse {
            return RankResponse(
                characterName = rank.characterName,
                characterLevel = rank.characterLevel,
                className = rank.className,
                subClassName = rank.subClassName ?: "",
                characterPopularity = rank.characterPopularity,
                characterGuildname = rank.characterGuildName ?: "",
                date = rank.snappedDate.toString()
            )
        }
    }

    fun to(): Rank {
        return Rank(
            characterName = characterName,
            characterLevel = characterLevel,
            className = className,
            subClassName = subClassName,
            characterPopularity = characterPopularity,
            characterGuildName = characterGuildname,
            snappedDate = date?.toLocalDateTime(),
            worldName = worldName ?: "UNDEFINED"
        )
    }


}
package org.maple.domain.rank

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.time.OffsetDateTime

@Repository
interface RankRepository : JpaRepository<Rank,Long> {
    @Query("SELECT r FROM Rank r WHERE r.snappedDate = :date")
    fun findAllByDateIs(date: LocalDateTime): List<Rank>
    fun findByCharacterName(characterName: String): List<Rank>
    fun findByCharacterLevel(characterLevel: Int): List<Rank>
    fun findByClassName(className: String): List<Rank>
    fun findBySubClassName(subClassName: String): List<Rank>
    fun findByCharacterPopularity(characterPopularity: Int): List<Rank>
    fun findByCharacterGuildName(characterGuildName: String): List<Rank>
}

package org.maple.domain.rank

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import org.maple.domain.rank.constant.getDefault
import java.time.LocalDateTime

@Entity
@Table(name = "ranks", indexes = [Index(name = "idx_snapped_date", columnList = "snapped_date, characterName")])
data class Rank(
    @Column(name = "snapped_date", columnDefinition = "TIMESTAMP")
    @NotNull
    val snappedDate: LocalDateTime? = LocalDateTime.now(),

    val worldName: String? = getDefault<String>(),
    val ranking: Int? = getDefault<Int>(),
    val characterName: String? = getDefault<String>(),
    val characterLevel: Int? = getDefault<Int>(),
    val characterExp: Long? = getDefault<Long>(),
    val className: String? = getDefault<String>(),
    val subClassName: String? = getDefault<String>(),
    val characterPopularity: Int? = getDefault<Int>(),
    val characterGuildName: String? = getDefault<String>()

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

}
package org.maple.domain.user

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.maple.domain.common.random.generateExtId
import java.util.UUID

@Entity
class User(
    var name: String? = ""
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    val extId: String = generateExtId()
}
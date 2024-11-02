package org.maple.domain.users

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.OffsetDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
class User(
    val name: String = "",
    val email: String = "",
    val role: String = "",
    val phone: String = "",
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
    val status: UserStatus = UserStatus.ACTIVE

    @CreatedDate
    val createdAt: OffsetDateTime = OffsetDateTime.now()
}
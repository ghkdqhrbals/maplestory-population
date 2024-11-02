package org.maple.domain.oauth

import jakarta.persistence.*
import org.maple.domain.users.User

@Entity
class OAuthProvider(
    val providerId: String,
    @Enumerated(EnumType.STRING)
    val providerKind: ProviderKind,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @ManyToOne(fetch = FetchType.LAZY)
    val user: User? = null
}
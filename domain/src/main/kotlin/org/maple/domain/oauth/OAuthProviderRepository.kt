package org.maple.domain.oauth

import org.springframework.data.jpa.repository.JpaRepository

interface OAuthProviderRepository : JpaRepository<Long, OAuthProvider> {
    fun findByProviderId(providerId: String): OAuthProvider?
}
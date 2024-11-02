package org.maple.domain.oauth.dto

import org.maple.domain.oauth.ProviderKind
import org.maple.domain.users.Gender

data class ThirdPartyUser(
    val providerId: String,
    val providerKind: ProviderKind,
    val email: String?,
    val name: String?,
    val phone: String?,
    val birth: String?,
    val gender: Gender?,
) {
}
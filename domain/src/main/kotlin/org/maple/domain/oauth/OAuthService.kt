package com.fooddash.user.service.users.oauth

import org.maple.domain.oauth.OAuthProvider
import org.maple.domain.oauth.ProviderKind
import org.maple.domain.oauth.dto.ThirdPartyUser

interface OAuthService {
    fun providerKind(): ProviderKind
    fun getUserInfo(accessToken: String): ThirdPartyUser
    fun isRegistered(providerId: String): OAuthProvider?
    fun login(accessToken: String)
}

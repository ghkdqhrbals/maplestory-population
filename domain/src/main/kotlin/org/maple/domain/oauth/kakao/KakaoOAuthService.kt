package org.maple.domain.oauth.kakao

import com.fooddash.user.service.users.oauth.AbstractOAuthService
import org.maple.domain.common.error.BadRequestException
import org.maple.domain.common.util.fromJson
import org.maple.domain.oauth.OAuthProvider
import org.maple.domain.oauth.OAuthProviderRepository
import org.maple.domain.oauth.ProviderKind
import org.maple.domain.oauth.dto.ThirdPartyUser
import org.maple.domain.users.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate

class KakaoOAuthService(
    @Value("\${kakao.api.host}")
    private val host: String,
    private val userRepository: UserRepository,
    private val oAuthProviderRepository: OAuthProviderRepository,
    private val commonRestTemplate: RestTemplate,
): AbstractOAuthService() {
    override fun providerKind() = ProviderKind.KAKAO

    override fun getUserInfo(accessToken: String): ThirdPartyUser {
        val url = "$host/v1/user/access_token_info"
        try {
            val response = commonRestTemplate.exchange(
                url,
                HttpMethod.GET,
                HttpEntity(
                    null,
                    HttpHeaders().apply {
                        setBearerAuth(accessToken)
                    },
                ),
                String::class.java,
            ).body!!
            return response.fromJson()
        } catch (e: Exception) {
            throw BadRequestException(detail = "kakao auth issue token error. error => ${e.message}")
        }
    }

    override fun isRegistered(providerId: String): OAuthProvider? {
        return oAuthProviderRepository.findByProviderId(providerId)
    }

    override fun login(accessToken: String) {

    }
}
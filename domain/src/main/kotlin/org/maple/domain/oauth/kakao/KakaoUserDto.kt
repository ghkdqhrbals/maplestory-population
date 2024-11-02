package com.fooddash.user.service.users.kakao.dto

import com.fasterxml.jackson.annotation.JsonInclude
import org.maple.domain.oauth.ProviderKind
import org.maple.domain.oauth.dto.ThirdPartyUser
import org.maple.domain.users.Gender
import java.time.ZonedDateTime

data class KakaoUserDto(
    val id: String,
    val connectedAt: ZonedDateTime,
    val kakaoAccount: KakaoAccountDto,
) {
    fun toThirdPartyUserInfoDto(): ThirdPartyUser {
        val birth = listOf(kakaoAccount.birthyear, kakaoAccount.birthday).let {
            if (it.all { item -> item != null }) {
                it.joinToString("")
            } else {
                null
            }
        }

        return ThirdPartyUser(
            providerId = id,
            providerKind = ProviderKind.KAKAO,
            email = kakaoAccount.email,
            name = kakaoAccount.name,
            birth = birth,
            phone = kakaoAccount.phoneNumber,
            gender = kakaoAccount.gender?.let { Gender.find(it) },
        )
    }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
data class KakaoAccountDto(
    val name: String? = null,
    val hasEmail: Boolean?,
    val isEmailValid: Boolean?,
    val isEmailVerified: Boolean?,
    val email: String? = null,
    val hasPhoneNumber: Boolean?,
    val phoneNumber: String?,
    val hasBirthyear: Boolean?,
    val birthyear: String?,
    val hasBirthday: Boolean?,
    val birthday: String?,
    val birthdayType: String?,
    val hasGender: Boolean?,
    val gender: String? = "UNKNOWN",
    val ci: String? = null,
)

package com.bacteria.bestfilm.data.remote.dto

import com.bacteria.bestfilm.domain.entity.UserAuthEntity
import com.google.gson.annotations.SerializedName
import java.util.*

class LoginResponseDto(
    @SerializedName("access_token") val accessToken: String?,
    @SerializedName("token_type") val tokenType: String?,
    @SerializedName("expires_in") val expiresIn: Int,
    @SerializedName("refresh_token") val refreshToken: String?,
    @SerializedName("as:client_id") val clientId: String?,
    @SerializedName("username") val username: String?,
    @SerializedName("country_code") val countryCode: String?,
) {
    fun toEntity(): UserAuthEntity {
        return UserAuthEntity(accessToken, tokenType, refreshToken, expiresIn)
    }
}


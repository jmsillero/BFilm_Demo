package com.bacteria.bestfilm.domain.entity

data class UserAuthEntity(
    val accessToken: String?,
    val tokenType: String?,
    val refreshToken: String?,
    val expireInt: Int
) {
}
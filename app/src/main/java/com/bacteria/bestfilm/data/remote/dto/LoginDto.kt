package com.bacteria.bestfilm.data.remote.dto

import com.bacteria.bestfilm.BuildConfig
import com.bacteria.bestfilm.data.remote.RemoteModule
import com.bacteria.bestfilm.domain.entity.LoginEntity
import java.net.URLEncoder

class LoginDto(
    val countryCode: String = BuildConfig.COUNTRY_CODE,
    val username: String,
    val password: String,
    val grantType: String,
    val clientId: String = BuildConfig.CLIENT_ID,
    val clientSecret: String = BuildConfig.CLIENT_SECRET
) {

    fun toEntity(): LoginEntity {
        return LoginEntity(username, password)
    }

    fun toRequest(): String {
        val encodedEmail = URLEncoder.encode(username, Charsets.UTF_8.toString())
        return "country_code=$countryCode&username=$encodedEmail&password=$password&grant_type=$grantType&client_id=$clientId&client_secret=$clientSecret"
    }


}

fun LoginEntity.toDto(): LoginDto {
    return LoginDto(
        grantType = "password",
        username = email,
        password = password!!
    )
}
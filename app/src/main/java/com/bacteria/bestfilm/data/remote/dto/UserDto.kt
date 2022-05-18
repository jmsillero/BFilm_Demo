package com.bacteria.bestfilm.data.remote.dto

import com.bacteria.bestfilm.domain.entity.MediaEntity
import com.bacteria.bestfilm.domain.entity.UserEntity
import com.google.gson.annotations.SerializedName

class UserDto(
    val email: String?,
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("last_name") val lastName: String?,
    @SerializedName("phone_number") val phoneNumber: String?,
    @SerializedName("profile_picture") val profilePicture: String?,
    @SerializedName("card_number") val cardNumber: String?
) {
    fun toEntity(): UserEntity {
        return UserEntity(email, firstName, lastName, phoneNumber, profilePicture, cardNumber)
    }
}
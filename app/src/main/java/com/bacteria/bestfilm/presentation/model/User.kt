package com.bacteria.bestfilm.presentation.model

import com.bacteria.bestfilm.domain.entity.UserEntity
import com.google.gson.annotations.SerializedName

class User(
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val phoneNumber: String?,
    val profilePicture: String?,
    val cardNumber: String?
) {


}

fun UserEntity.toUser(): User {
    return User(email, firstName, lastName, phoneNumber, profilePicture, cardNumber)
}
package com.bacteria.bestfilm.domain.repository

import com.bacteria.bestfilm.domain.entity.LoginEntity
import com.bacteria.bestfilm.domain.entity.UserAuthEntity
import com.bacteria.bestfilm.domain.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun login(loginData: LoginEntity): Flow<UserAuthEntity>
    suspend fun getProfile(countryCode: String): Flow<UserEntity>
}
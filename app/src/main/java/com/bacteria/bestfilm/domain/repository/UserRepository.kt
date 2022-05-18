package com.bacteria.bestfilm.domain.repository

import com.bacteria.bestfilm.data.remote.dto.LoginDto
import com.bacteria.bestfilm.domain.entity.LoginEntity
import com.bacteria.bestfilm.domain.entity.UserAuthEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun login(loginData: LoginEntity): Flow<UserAuthEntity>
}
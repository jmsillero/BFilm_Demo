package com.bacteria.bestfilm.domain.usecase

import com.bacteria.bestfilm.domain.entity.LoginEntity
import com.bacteria.bestfilm.domain.entity.UserAuthEntity
import com.bacteria.bestfilm.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Login @Inject constructor(private val userRepository: UserRepository) {
    suspend fun invoke(loginEntity: LoginEntity): Flow<UserAuthEntity> {
        return userRepository.login(loginEntity)
    }
}
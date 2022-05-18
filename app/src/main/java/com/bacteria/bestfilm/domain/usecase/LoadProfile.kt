package com.bacteria.bestfilm.domain.usecase

import com.bacteria.bestfilm.domain.entity.UserEntity
import com.bacteria.bestfilm.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadProfile @Inject constructor(private val userRepository: UserRepository) {
    suspend fun invoke(countryCode: String): Flow<UserEntity> {
        return userRepository.getProfile(countryCode)
    }
}
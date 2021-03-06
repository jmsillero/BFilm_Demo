package com.bacteria.bestfilm.domain.repository.impl

import com.bacteria.bestfilm.data.cache.preferences.EncryptedPreferences
import com.bacteria.bestfilm.data.cache.preferences.impl.EncryptedPreferencesImpl
import com.bacteria.bestfilm.data.remote.datasource.RemoteUserDatasource
import com.bacteria.bestfilm.data.remote.dto.toDto
import com.bacteria.bestfilm.domain.entity.LoginEntity
import com.bacteria.bestfilm.domain.entity.UserAuthEntity
import com.bacteria.bestfilm.domain.entity.UserEntity
import com.bacteria.bestfilm.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteUserDatasource: RemoteUserDatasource,
    private val encryptedPreferences: EncryptedPreferences
) :
    UserRepository {
    override suspend fun login(loginData: LoginEntity): Flow<UserAuthEntity> {
        val response = remoteUserDatasource.login(loginData.toDto())
        return response.map { resp ->
            resp.toEntity()
        }.onEach {
            encryptedPreferences.saveToken("${it.tokenType} ${it.accessToken}")
        }
    }

    override suspend fun getProfile(countryCode: String): Flow<UserEntity> {
        val token = encryptedPreferences.getToken()
        val response = remoteUserDatasource.getProfile(token!!, countryCode)
        return response.map { resp ->
            resp.toEntity()
        }.onEach {
            //TODO: save token
        }
    }
}
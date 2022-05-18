package com.bacteria.bestfilm.data.remote.datasource.impl

import com.bacteria.bestfilm.data.remote.RemoteService
import com.bacteria.bestfilm.data.remote.datasource.RemoteUserDatasource
import com.bacteria.bestfilm.data.remote.dto.LoginDto
import com.bacteria.bestfilm.data.remote.dto.LoginResponseDto
import com.bacteria.bestfilm.data.remote.errors.ErrorUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.URLEncoder
import javax.inject.Inject
import kotlin.math.log

class RemoteUserDatastoreImpl @Inject constructor(private val service: RemoteService) :
    RemoteUserDatasource {
    override suspend fun login(loginData: LoginDto): Flow<LoginResponseDto> {
        try {
            val userResult = service.login(loginData.toRequest())
            val body = userResult.body()
            if (userResult.isSuccessful && body != null) {
                return flow {
                    emit(body)
                }
            }
            return flow {
                ErrorUtils.parseError(userResult).message()?.let { error(it) }
            }
        } catch (e: Exception) {
            throw e
        }
    }
}
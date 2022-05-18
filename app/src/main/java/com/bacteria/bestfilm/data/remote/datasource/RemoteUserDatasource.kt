package com.bacteria.bestfilm.data.remote.datasource

import com.bacteria.bestfilm.data.remote.dto.LoginDto
import com.bacteria.bestfilm.data.remote.dto.LoginResponseDto
import kotlinx.coroutines.flow.Flow


interface RemoteUserDatasource {
    suspend fun login(loginData: LoginDto): Flow<LoginResponseDto>
}
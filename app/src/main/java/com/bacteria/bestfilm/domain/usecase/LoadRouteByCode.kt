package com.bacteria.bestfilm.domain.usecase

import com.bacteria.bestfilm.domain.entity.RouteEntity
import com.bacteria.bestfilm.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadRouteByCode @Inject constructor(private val filmRepository: FilmRepository) {
    suspend fun invoke(code: String): Flow<RouteEntity> {
        return filmRepository.loadRouteByCode(code)
    }
}
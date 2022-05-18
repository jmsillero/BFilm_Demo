package com.bacteria.bestfilm.domain.usecase

import com.bacteria.bestfilm.domain.entity.FilmEntity
import com.bacteria.bestfilm.domain.entity.FilmListEntity
import com.bacteria.bestfilm.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadFilmById @Inject constructor(private val filmRepository: FilmRepository) {
    suspend fun invoke(id: Long): Flow<FilmEntity> {
        return filmRepository.getFilmById(id)
    }
}
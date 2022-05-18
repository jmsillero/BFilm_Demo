package com.bacteria.bestfilm.domain

import com.bacteria.bestfilm.data.cache.EncryptedPreferences
import com.bacteria.bestfilm.data.cache.datasource.LocalFilmsDatasource
import com.bacteria.bestfilm.data.remote.datasource.RemoteFilmDatasource
import com.bacteria.bestfilm.data.remote.datasource.RemoteUserDatasource
import com.bacteria.bestfilm.domain.repository.FilmRepository
import com.bacteria.bestfilm.domain.repository.UserRepository
import com.bacteria.bestfilm.domain.repository.impl.FilmRepositoryImpl
import com.bacteria.bestfilm.domain.repository.impl.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun userRepositoryProvider(
        remoteUserDatasource: RemoteUserDatasource,
        encryptedPreferences: EncryptedPreferences
    ): UserRepository {
        return UserRepositoryImpl(remoteUserDatasource, encryptedPreferences)
    }

    @Provides
    @Singleton
    fun filmRepositoryProvider(
        remoteFilmDatasource: RemoteFilmDatasource,
        localFilmsDatasource: LocalFilmsDatasource
    ): FilmRepository {
        return FilmRepositoryImpl(remoteFilmDatasource, localFilmsDatasource)
    }
}
package com.bacteria.bestfilm.data.remote

import com.bacteria.bestfilm.data.remote.datasource.RemoteFilmDatasource
import com.bacteria.bestfilm.data.remote.datasource.RemoteUserDatasource
import com.bacteria.bestfilm.data.remote.datasource.impl.RemoteFilmDatastoreImpl
import com.bacteria.bestfilm.data.remote.datasource.impl.RemoteUserDatastoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    fun buildService(): RemoteService {
        val retrofit = ServiceGenerator.buildRetrofit()
        return retrofit.create(RemoteService::class.java)
    }


    @Provides
    @Singleton
    fun provideUserRemoteDatasource(
        remoteService: RemoteService
    ): RemoteUserDatasource {
        return RemoteUserDatastoreImpl(remoteService)
    }

    @Provides
    @Singleton
    fun provideFilmRemoteDatasource(remoteService: RemoteService): RemoteFilmDatasource {
        return RemoteFilmDatastoreImpl(remoteService)
    }
}
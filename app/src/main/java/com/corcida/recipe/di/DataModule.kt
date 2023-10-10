package com.corcida.recipe.di

import com.corcida.data.repository.RecipesRepository
import com.corcida.data.source.LocalDataSource
import com.corcida.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun moviesRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ) = RecipesRepository(localDataSource, remoteDataSource)

}
package com.corcida.recipe.di

import android.app.Application
import androidx.room.Room
import com.corcida.data.source.LocalDataSource
import com.corcida.data.source.RemoteDataSource
import com.corcida.recipe.framework.local.Database
import com.corcida.recipe.framework.local.LocalDataSourceImpl
import com.corcida.recipe.framework.local.dao.RecipeDao
import com.corcida.recipe.framework.remote.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application): Database = Room.databaseBuilder(
        app,
        Database::class.java,
        "recipe-db"
    ).build()

    @Provides
    fun recipeDao(db: Database): RecipeDao = db.recipeDao()

    @Provides
    fun localDataSourceProvider(recipeDao: RecipeDao): LocalDataSource = LocalDataSourceImpl(recipeDao)

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = RemoteDataSourceImpl()

}
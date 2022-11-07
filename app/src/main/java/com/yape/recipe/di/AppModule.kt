package com.yape.recipe.di

import com.yape.recipe.data.database.dao.RecipeDao
import com.yape.recipe.data.network.RecipeRemoteDataSource
import com.yape.recipe.data.repository.RecipeRepository
import com.yape.recipe.domain.uc.GetRecipesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRecipeRepository(remoteDataSource: RecipeRemoteDataSource,
                                       localDataSource: RecipeDao
    ): RecipeRepository = RecipeRepository(remoteDataSource, localDataSource)

    @Provides
    fun provideRecipeUseCase(repository: RecipeRepository):
            GetRecipesUseCase = GetRecipesUseCase(repository)

}
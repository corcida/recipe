package com.corcida.recipe.di

import com.corcida.data.repository.RecipesRepository
import com.corcida.usecases.FindRecipeUseCase
import com.corcida.usecases.GetRecipesUseCase
import com.corcida.usecases.ToggleRecipeFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Provides
    fun getRecipesUseCase(
        repository: RecipesRepository
    ) = GetRecipesUseCase(repository)

    @Provides
    fun findRecipeUseCase(
        repository: RecipesRepository
    ) = FindRecipeUseCase(repository)

    @Provides
    fun toggleRecipeFavoriteUseCase(
        repository: RecipesRepository
    ) = ToggleRecipeFavoriteUseCase(repository)
}
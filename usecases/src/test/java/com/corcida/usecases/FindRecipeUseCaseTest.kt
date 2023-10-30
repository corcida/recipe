package com.corcida.usecases

import com.corcida.data.repository.RecipesRepository
import com.corcida.data.source.LocalDataSource
import com.corcida.data.source.RemoteDataSource
import com.corcida.domain.Location
import com.corcida.domain.Recipe
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyBlocking

class FindRecipeUseCaseTest{

    @Test
    fun `When find recipe is invoked then returns repository find recipe is called`()  {
        //Given
        val repository = mock<RecipesRepository>(){
            onBlocking { findRecipe(1) } doReturn (mock<Recipe>())
        }
        val useCase = FindRecipeUseCase(repository)

        //When
        runBlocking { useCase.invoke(1) }

        //Then
        verifyBlocking(repository) { findRecipe(1) }
    }

    @Test
    fun `When database has several values then returns the expected one`()  {
        //Given
        val testId = 1
        val expectedRecipe = Recipe(1, "test 1", "", "", listOf(), listOf(),
            Location("", 0.0, 0.0), false )
        val notExpectedRecipe = Recipe(2, "test 2", "", "", listOf(), listOf(),
            Location("", 0.0, 0.0), false )
        val listOfRecipes = listOf(expectedRecipe, notExpectedRecipe)
        val localDataSource = mock<LocalDataSource>(){
            onBlocking { findRecipe(testId) } doReturn(listOfRecipes.first { it.id == testId })
        }
        val remoteDataSource = mock<RemoteDataSource>()
        val repository = RecipesRepository(localDataSource, remoteDataSource)
        val useCase = FindRecipeUseCase(repository)

        //When
        val response = runBlocking { useCase.invoke(testId) }

        //Then
        assert(response == expectedRecipe)
    }

}
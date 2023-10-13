package com.corcida.usecases

import com.corcida.data.repository.RecipesRepository
import com.corcida.domain.Recipe
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verifyBlocking

class GetRecipesTest {

    @Test
    fun `When database has already data then server is not called`()  {
        //Given
        val repository = mock<RecipesRepository>(){
            onBlocking { isDatabaseEmpty() } doReturn (false)
        }
        val useCase = GetRecipes(repository)

        //When
        runBlocking { useCase.invoke() }

        //Then
        verifyBlocking(repository, times(0)) { getRecipesFromServer() }
    }

    @Test
    fun `When database has no data then server is called and data is saved into database`()  {
        //Given
        val repository = mock<RecipesRepository>(){
            onBlocking { isDatabaseEmpty() } doReturn (true)
            onBlocking { getRecipesFromServer() } doReturn (listOf())
        }
        val useCase = GetRecipes(repository)

        //When
        runBlocking { useCase.invoke() }

        //Then
        verifyBlocking(repository) { getRecipesFromServer() }
        verifyBlocking(repository) { (saveRecipes(listOf())) }
    }

    @Test
    fun `When get recipe is invoked then returns the data from database `()  {
        //Given
        val listOfRecipes = mock<List<Recipe>>()
        val repository = mock<RecipesRepository>(){
            onBlocking { isDatabaseEmpty() } doReturn (false)
            onBlocking { getRecipesFromDatabase() } doReturn (flowOf(listOfRecipes))
        }
        val useCase = GetRecipes(repository)

        //When
        val response = runBlocking { useCase.invoke() }

        //Then
        runBlocking { assert(response.first() == flowOf(listOfRecipes).first()) }
    }

}
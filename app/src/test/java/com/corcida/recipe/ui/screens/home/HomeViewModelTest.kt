package com.corcida.recipe.ui.screens.home

import com.corcida.recipe.fakes.FakeRecipes
import com.corcida.recipe.rules.MainDispatcherRule
import com.corcida.usecases.GetRecipes
import com.corcida.usecases.ToggleRecipeFavorite
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    //system in test
    private lateinit var viewModel: HomeViewModel

    //dependencies
    private lateinit var getRecipes: GetRecipes
    private lateinit var toggleRecipeFavorite: ToggleRecipeFavorite

    //rules
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun onBefore(){
        getRecipes = mock<GetRecipes>(){
            onBlocking { invoke() } doReturn (flowOf(FakeRecipes.fakeRecipes))
        }
        toggleRecipeFavorite = mock<ToggleRecipeFavorite>()
        viewModel = HomeViewModel(getRecipes, toggleRecipeFavorite)
    }
    @Test
    fun `When the user enters a query then the view model filters the recipes`() = runTest {
        //Given
        val expectedRecipe =  listOf (FakeRecipes.expectedRecipe)

        //When
        launch { viewModel.getRecipesData() }
        viewModel.queryRecipe("tag 1")
        advanceUntilIdle()

        //Then
        runBlocking { assert(viewModel.recipes.value == expectedRecipe) }
    }

    @Test
    fun `When the user enters two queries then the view model filters the recipes twice`() = runTest {
        //Given
        val expectedRecipe =  listOf (FakeRecipes.expectedRecipe)

        //When
        launch { viewModel.getRecipesData() }
        viewModel.queryRecipe("tag 2")
        viewModel.queryRecipe("tag 1")
        advanceUntilIdle()

        //Then
        runBlocking { assert(viewModel.recipes.value == expectedRecipe) }
    }

    @Test
    fun `When the user enters an empty query then the view model returns all the recipes`() = runTest {
        //Given
        val expectedRecipe =  FakeRecipes.fakeRecipes

        //When
        launch { viewModel.getRecipesData() }
        viewModel.queryRecipe("")
        advanceUntilIdle()

        //Then
        runBlocking {  assert(viewModel.recipes.value == expectedRecipe) }
    }
}
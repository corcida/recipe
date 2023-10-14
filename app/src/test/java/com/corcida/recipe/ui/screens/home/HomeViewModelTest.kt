package com.corcida.recipe.ui.screens.home

import com.corcida.recipe.fakes.FakeRecipes
import com.corcida.recipe.rules.MainDispatcherRule
import com.corcida.usecases.GetRecipes
import com.corcida.usecases.ToggleRecipeFavorite
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
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
    fun setUp(){
        getRecipes = mock(){
            onBlocking { invoke() } doReturn (flowOf(FakeRecipes.fakeRecipes))
        }
        toggleRecipeFavorite = mock<ToggleRecipeFavorite>()
        viewModel = HomeViewModel(getRecipes, toggleRecipeFavorite)
    }

    @Test
    fun `When the user enters to the screen then the view model get all the recipes`() = runTest {
        //Given
        val expectedRecipe =  FakeRecipes.fakeRecipes

        //When
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.recipes.collect()
        }
        launch { viewModel.getRecipesData() }
        advanceUntilIdle()

        //Then
        runBlocking { assert(viewModel.recipes.value == expectedRecipe) }
    }
    @Test
    fun `When the user enters a query with exact tag value then the view model filters the recipes`() = runTest {
        //Given
        val expectedRecipe =  listOf (FakeRecipes.expectedRecipe)

        //When
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.recipes.collect()
        }
        launch { viewModel.getRecipesData() }
        viewModel.queryRecipe("tag 1")
        advanceUntilIdle()

        //Then
        runBlocking { assert(viewModel.recipes.value == expectedRecipe) }
    }

    @Test
    fun `When the user enters two queries with exact tag value then the view model filters the recipes twice`() = runTest {
        //Given
        val expectedRecipe =  listOf (FakeRecipes.expectedRecipe)

        //When
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.recipes.collect()
        }
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
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.recipes.collect()
        }
        launch { viewModel.getRecipesData() }
        viewModel.queryRecipe("")
        advanceUntilIdle()

        //Then
        runBlocking { assert(viewModel.recipes.value == expectedRecipe) }
    }

    @Test
    fun `When the user enters a query with not exact tag or ingredient value then the view model filters the recipes`() = runTest {
        //Given
        val expectedRecipe =  listOf (FakeRecipes.expectedRecipe)

        //When
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.recipes.collect()
        }
        launch { viewModel.getRecipesData() }
        viewModel.queryRecipe("expected")
        advanceUntilIdle()

        //Then
        runBlocking { assert(viewModel.recipes.value == expectedRecipe) }
    }
}
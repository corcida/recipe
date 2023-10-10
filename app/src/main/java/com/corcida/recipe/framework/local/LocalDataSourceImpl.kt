package com.corcida.recipe.framework.local

import com.corcida.data.source.LocalDataSource
import com.corcida.domain.Recipe
import com.corcida.recipe.framework.local.dao.RecipeDao
import com.corcida.recipe.framework.toDomainRecipe
import com.corcida.recipe.framework.toRoomRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LocalDataSourceImpl (private val recipeDao: RecipeDao) : LocalDataSource {
    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { recipeDao.recipeCount() <= 0 }


    override suspend fun saveRecipes(recipes: List<Recipe>) = withContext(Dispatchers.IO) {
        recipeDao.insertRecipes(recipes.map { it.toRoomRecipe() })
    }

    override fun getRecipes(): Flow<List<Recipe>> =
        recipeDao.getAllRecipes()
            .flowOn(Dispatchers.IO)
            .distinctUntilChanged()
            .map { entities -> entities.map { it.toDomainRecipe() } }

    override suspend fun findRecipe(id: Int): Recipe = withContext(Dispatchers.IO) {
       recipeDao.findRecipe(id).toDomainRecipe()
    }

    override suspend fun update(recipe: Recipe) = withContext(Dispatchers.IO) {
        recipeDao.updateRecipe(recipe.toRoomRecipe())
    }

}
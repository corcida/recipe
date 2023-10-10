package com.corcida.recipe.framework.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.corcida.recipe.framework.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe")
    fun getAllRecipes(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipe WHERE id = :id")
    fun findRecipe(id: Int): RecipeEntity

    @Query("SELECT COUNT(id) FROM recipe")
    fun recipeCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipes(movies: List<RecipeEntity>)

    @Update
    fun updateRecipe(recipe: RecipeEntity)
}
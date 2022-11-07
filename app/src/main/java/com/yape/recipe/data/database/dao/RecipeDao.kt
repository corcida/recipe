package com.yape.recipe.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yape.recipe.data.database.entities.RecipeEntity

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe")
    suspend fun getRecipes() : List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<RecipeEntity>)

    @Query("DELETE FROM recipe")
    suspend fun deleteAll()

}
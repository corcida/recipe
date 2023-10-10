package com.corcida.recipe.framework.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.corcida.recipe.framework.local.dao.RecipeDao
import com.corcida.recipe.framework.local.entity.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 1)
abstract class Database : RoomDatabase(){

    abstract fun recipeDao(): RecipeDao
}
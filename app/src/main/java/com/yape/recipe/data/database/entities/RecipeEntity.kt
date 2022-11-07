package com.yape.recipe.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.yape.recipe.data.database.converters.LocationConverter
import com.yape.recipe.domain.model.Location
import com.yape.recipe.domain.model.Recipe

@Entity(tableName = "recipe")
@TypeConverters(LocationConverter::class)
data class RecipeEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val photo: String,
    val description: String,
    val duration: Int,
    val location: Location
)

fun Recipe.toDatabase() = RecipeEntity(id, title, photo, description, duration, location)

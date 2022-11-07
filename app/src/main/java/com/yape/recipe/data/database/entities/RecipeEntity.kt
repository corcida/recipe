package com.yape.recipe.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.yape.recipe.data.database.converters.LocationConverter
import com.yape.recipe.domain.model.Location

@Entity(tableName = "recipe")
@TypeConverters(LocationConverter::class)
data class RecipeEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val location: Location
)

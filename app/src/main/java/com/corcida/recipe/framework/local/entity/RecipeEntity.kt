package com.corcida.recipe.framework.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.corcida.domain.Location
import com.corcida.recipe.framework.local.converter.RecipeConverters

@Entity(tableName = "recipe")
@TypeConverters(RecipeConverters::class)
data class RecipeEntity (
    @PrimaryKey
    val id: Int,
    val title: String,
    val overview: String,
    val image: String,
    val ingredients: List<String>,
    val tags: List<String>,
    val location: Location,
    val favorite: Boolean
)
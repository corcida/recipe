package com.corcida.domain

data class Recipe(
    val id: Int,
    val title: String,
    val overview: String,
    val image: String,
    val location: Location,
    val favorite: Boolean
)
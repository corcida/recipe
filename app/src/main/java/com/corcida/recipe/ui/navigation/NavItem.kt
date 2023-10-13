package com.corcida.recipe.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem(
    internal val baseRoute: String,
    private val navArgs: List<NavArgs> = emptyList()
){
    object HomeNavItem : NavItem("home")
    object DetailNavItem : NavItem("detail", listOf(NavArgs.RecipeId)) {
        fun createRoute(mediaId: Int) = "$baseRoute/$mediaId"
    }
    object MapNavItem : NavItem("map", listOf(NavArgs.RecipeId)) {
        fun createRoute(mediaId: Int) = "$baseRoute/$mediaId"
    }

    val route = run {
        val argValues = navArgs.map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argValues)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }
}

enum class NavArgs(val key: String, val navType: NavType<*>) {
    RecipeId("recipeId", NavType.IntType)
}

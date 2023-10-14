package com.corcida.recipe.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.corcida.recipe.MainActivity
import com.corcida.recipe.ui.screens.detail.DetailScreen
import com.corcida.recipe.ui.screens.detail.DetailViewModel
import com.corcida.recipe.ui.screens.home.HomeScreen
import com.corcida.recipe.ui.screens.home.HomeViewModel
import com.corcida.recipe.ui.screens.map.MapScreen
import com.corcida.recipe.ui.screens.map.MapViewModel

@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavItem.HomeNavItem.route
    ) {
        composable(NavItem.HomeNavItem) { navBackStack ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStack)
            val viewModel: HomeViewModel = viewModel(LocalContext.current as MainActivity, "HomeViewModel", factory)
            HomeScreen(
                onNavigate = { navController.navigate(NavItem.DetailNavItem.createRoute(it)) },
                viewModel
            )
        }
        composable(NavItem.DetailNavItem) { navBackStack ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStack)
            val viewModel: DetailViewModel = viewModel(LocalContext.current as MainActivity, "DetailViewModel", factory)
            DetailScreen(
                onNavigate = { navController.navigate(NavItem.MapNavItem.createRoute(it))  },
                onBackPressed = { navController.popBackStack() },
                id = navBackStack.findArg(NavArgs.RecipeId.key),
                viewModel
            )
        }
        composable(NavItem.MapNavItem) { navBackStack ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStack)
            val viewModel: MapViewModel = viewModel(LocalContext.current as MainActivity, "MapViewModel", factory)
            MapScreen(
                onBackPressed = { navController.popBackStack() },
                id = navBackStack.findArg(NavArgs.RecipeId.key),
                viewModel
            )
        }
    }
}
private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(key: String): T {
    val value = arguments?.get(key)
    requireNotNull(value)
    return value as T
}
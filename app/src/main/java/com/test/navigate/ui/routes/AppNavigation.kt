package com.test.navigate.ui.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.navigate.ui.screens.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

sealed class NavigationScreens(val route: String, val one: String, val two: String) {
    object HomeScreen: NavigationScreens("home", "", "")
    object Addition : NavigationScreens("add", "first/{one}", "second/{two}")
    object Subtraction : NavigationScreens("subtract", "first/{one}", "second/{two}")
}

fun navigate(
    argument1: String? = null,
    argument2: String? = null,
    screen: NavigationScreens,
    navController: NavController
) {
    var route = screen.route
    // If argument is supplied, navigate using that argument
    argument1?.let {
        route = screen.route.plus(it)
    }
    argument2?.let {
        route = route.plus(it)
    }
    navController.navigate(route)
}

@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()
    //val viewModel: CalculatorViewModel = remember { CalculatorViewModel() } // to keep the state

    NavHost(
        navController = navController,
        startDestination = NavigationScreens.HomeScreen.route
    ) {
        composable(NavigationScreens.HomeScreen.route) {
            HomeScreen(
                navController = navController
            )
        }
        composable(NavigationScreens.Addition.route.plus(NavigationScreens.Addition.one).plus(NavigationScreens.Addition.two)) { backStackEntry ->
            AdditionScreen(
                navController,
                navigationViewModel(backStackEntry = navController.getBackStackEntry("home")),
                backStackEntry.arguments?.getString("one")?: "",
                backStackEntry.arguments?.getString("two")?: ""
            )
        }
        composable(NavigationScreens.Subtraction.route.plus(NavigationScreens.Subtraction.one).plus(NavigationScreens.Subtraction.two)) { backStackEntry ->
            SubtractionScreen(
                navController,
                backStackEntry.arguments?.getString("one")?: "",
                backStackEntry.arguments?.getString("two")?: ""
            )
        }
    }
}

@Composable
inline fun <reified VM : ViewModel> navigationViewModel(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null,
    backStackEntry: NavBackStackEntry
): VM {
    return remember(qualifier, parameters) {
        backStackEntry.getViewModel(qualifier, parameters)
    }
}
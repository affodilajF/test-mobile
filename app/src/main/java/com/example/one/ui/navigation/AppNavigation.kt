package com.example.one.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.one.ui.screen.FirstScreen
import com.example.one.ui.screen.SecondScreen
import com.example.one.ui.screen.ThirdScreen

@Composable
fun AppNavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.FIRST_SCREEN) {
        composable(Routes.FIRST_SCREEN) {
            FirstScreen(navController = navController)
        }
        composable(
            route = "${Routes.SECOND_SCREEN}/{nameValue}",
            arguments = listOf(navArgument("nameValue") { type = NavType.StringType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val nameValue = arguments.getString("nameValue") ?: ""
            SecondScreen(navController = navController, nameValue = nameValue)
        }
        composable(Routes.THIRD_SCREEN) {
//            ThirdScreen(navController = navController)
            ThirdScreen(navController)
        }
    }
}



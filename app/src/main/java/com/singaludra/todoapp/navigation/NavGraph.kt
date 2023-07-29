package com.singaludra.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.singaludra.todoapp.ui.SharedViewModel
import com.singaludra.todoapp.ui.addentry.AddScreen
import com.singaludra.todoapp.ui.home.HomeScreen

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            val entry = remember(it) {
                navController.getBackStackEntry(Screen.HomeScreen.route)
            }
            val sharedViewModel = hiltViewModel<SharedViewModel>(entry)
            HomeScreen(navController = navController, sharedViewModel)
        }
        composable(
            route = Screen.AddScreen.route
        ) {
            val entry = remember(it) {
                navController.getBackStackEntry(Screen.HomeScreen.route)
            }
            val sharedViewModel = hiltViewModel<SharedViewModel>(entry)
            AddScreen(navController = navController, sharedViewModel)
        }
    }
}
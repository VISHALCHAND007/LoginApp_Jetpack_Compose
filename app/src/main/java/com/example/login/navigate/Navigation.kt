package com.example.login.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login.screens.HomeScreen
import com.example.login.screens.SignInScreen
import com.example.login.screens.SignUpScreen

@Composable
fun Navigation() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = Screen.SignUpScreen.route) {
        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen(navigationController)
        }
        composable(route = Screen.SignInScreen.route) {
            SignInScreen(navigationController)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navigationController)
        }
    }
}
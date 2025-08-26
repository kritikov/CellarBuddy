package com.example.cellarbuddy.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cellarbuddy.infrastructure.Screen
import com.example.cellarbuddy.ui.screens.MainScreen

@Composable
fun Navigator() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Main
    ){
        composable<Screen.Main> {
            MainScreen()
        }
    }
}
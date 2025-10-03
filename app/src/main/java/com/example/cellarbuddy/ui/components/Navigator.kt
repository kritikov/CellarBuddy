package com.example.cellarbuddy.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.cellarbuddy.infrastructure.Screen
import com.example.cellarbuddy.ui.screens.CategoriesListScreen
import com.example.cellarbuddy.ui.screens.DrinkCategoriesListScreen
import com.example.cellarbuddy.ui.screens.MainScreen
import com.example.cellarbuddy.ui.screens.TypeCreateScreen
import com.example.cellarbuddy.ui.screens.TypeEditScreen
import com.example.cellarbuddy.ui.screens.TypesListScreen

@Composable
fun Navigator() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Main
    ){
        composable<Screen.Main> {
            MainScreen(
                onNavigateToDrinks = { navController.navigate(route = Screen.DrinkCategoriesList) },
                onNavigateToCategories = { navController.navigate(route = Screen.CategoriesList) },
                onNavigateToTypes = { navController.navigate(route = Screen.TypesList)},
                onNavigateToScan = {},
            )
        }

        composable<Screen.TypesList>{
            TypesListScreen(
                onNavigateToAdd = { navController.navigate(route = Screen.TypeCreate)},
                onNavigateToEdit = { typeId ->
                    navController.navigate(route = Screen.TypeEdit(id = typeId))
                },
                onBack = {
                    navController.popBackStack()
                },
            )
        }

        composable<Screen.TypeCreate>{
            TypeCreateScreen(
                onBack = {
                    navController.popBackStack()
                },
            )
        }

        composable<Screen.TypeEdit>{
            val args = it.toRoute<Screen.TypeEdit>()
            TypeEditScreen(
                id = args.id,
                onBack = {
                    navController.popBackStack()
                },
            )
        }

        composable<Screen.CategoriesList>{
            CategoriesListScreen(
                onNavigateToAdd = {},
                onNavigateToEdit = {},
                onBack = {
                    navController.popBackStack()
                },
            )
        }

        composable<Screen.DrinkCategoriesList>{
            DrinkCategoriesListScreen(
                onNavigateToDrinks = {},
                onBack = {
                    navController.popBackStack()
                },
            )
        }
    }
}
package com.example.cellarbuddy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cellarbuddy.ui.components.BaseScreen
import com.example.cellarbuddy.viewModels.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onNavigateToDrinks: () -> Unit,
    onNavigateToCategories: () -> Unit,
    onNavigateToTypes: () -> Unit,
    onNavigateToScan: () -> Unit,
) {

    // doesn't do anything actually
    LaunchedEffect(Unit) {
        viewModel.loadOnce()
    }

    MainContents(
        onNavigateToDrinks = onNavigateToDrinks,
        onNavigateToCategories = onNavigateToCategories,
        onNavigateToTypes = onNavigateToTypes,
        onNavigateToScan = onNavigateToScan,
    )
}

@Composable
fun MainContents(
    onNavigateToDrinks: () -> Unit,
    onNavigateToCategories: () -> Unit,
    onNavigateToTypes: () -> Unit,
    onNavigateToScan: () -> Unit,
) {
    BaseScreen(
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(
                    onClick = onNavigateToDrinks
                ){
                    Text(text = "Drinks")
                }

                Button(
                    onClick = onNavigateToCategories
                ){
                    Text(text = "Categories")
                }

                Button(
                    onClick = onNavigateToTypes
                ){
                    Text(text = "Types")
                }

                Button(
                    onClick = onNavigateToScan
                ){
                    Text(text = "Scan")
                }

            }
        }
    )
}

@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
@Composable
fun MainPreview() {
    MainContents(
        onNavigateToDrinks = {},
        onNavigateToCategories = { },
        onNavigateToTypes = {},
        onNavigateToScan = { },
    )
}
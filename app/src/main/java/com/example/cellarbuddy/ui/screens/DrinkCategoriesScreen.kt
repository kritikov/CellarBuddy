package com.example.cellarbuddy.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.cellarbuddy.data.models.Category
import com.example.cellarbuddy.infrastructure.UiEvent
import com.example.cellarbuddy.ui.components.BaseScreen
import com.example.cellarbuddy.ui.components.CardLight
import com.example.cellarbuddy.ui.components.LazyColumnDark
import com.example.cellarbuddy.ui.components.TextSimple
import com.example.cellarbuddy.ui.components.WorkingIcon
import com.example.cellarbuddy.viewModels.DrinkCategoriesListViewModel
import kotlinx.coroutines.flow.map

@Composable
fun DrinkCategoriesListScreen(
    viewModel: DrinkCategoriesListViewModel = hiltViewModel(),
    onNavigateToDrinks: (Long)->Unit,
    onBack: ()->Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val categories by viewModel.uiState.map { it.categories }.collectAsState(initial = emptyList())
    val loadIsCompleted by viewModel.uiFlags.map {it.loadIsCompleted}.collectAsState(initial = false)

    // managing events sent by the viewModel
    LaunchedEffect(Unit) {
        viewModel.uiEvents.collect { event ->
            when (event){
                is UiEvent.Failed -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                else -> Unit // ignore any other case
            }
        }
    }

    // load the data once
    LaunchedEffect(Unit) {
        viewModel.loadOnce()
    }

    DrinkCategoriesListContents(
        snackbarHostState = snackbarHostState,
        categories = categories,
        loadIsCompleted = loadIsCompleted,
        onSelect = onNavigateToDrinks,
        onBack = onBack
    )
}

@Composable
fun DrinkCategoriesListContents(
    snackbarHostState: SnackbarHostState ?= null,
    categories: List<Category>,
    loadIsCompleted: Boolean,
    onSelect: (Long) -> Unit,
    onBack: () -> Unit,
){
    BaseScreen(
        title = "select a category",
        snackbarHostState = snackbarHostState,
        showBackButton = true,
        onBack = onBack,
        content = {
            Box(modifier = Modifier.fillMaxSize()){
                LazyColumnDark(
                    content = {
                        items(categories){category ->
                            CardLight(
                                onClick = { onSelect(category.id) },
                                content = {
                                    TextSimple(
                                        text = category.name
                                    )
                                }
                            )
                        }
                    }
                )

                if (!loadIsCompleted){
                    WorkingIcon()
                }
            }
        }
    )

}

@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
@Composable
fun DrinkCategoriesListPreview(){
    val categories = listOf(
        Category(1, "Whiskey"),
        Category(2, "Gin"),
        Category(3, "Vodka"),
    )

    DrinkCategoriesListContents(
        categories = categories,
        loadIsCompleted = true,
        onSelect = {},
        onBack = {}
    )
}
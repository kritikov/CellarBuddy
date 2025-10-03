package com.example.cellarbuddy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.cellarbuddy.data.models.Category
import com.example.cellarbuddy.infrastructure.UiEvent
import com.example.cellarbuddy.ui.components.BaseScreen
import com.example.cellarbuddy.ui.components.CardLight
import com.example.cellarbuddy.ui.components.LazyColumnDark
import com.example.cellarbuddy.ui.components.TextSimple
import com.example.cellarbuddy.ui.components.WorkingIcon
import com.example.cellarbuddy.ui.theme.Black
import com.example.cellarbuddy.ui.theme.VanillaCream
import com.example.cellarbuddy.viewModels.CategoriesListViewModel
import kotlinx.coroutines.flow.map

@Composable
fun CategoriesListScreen(
    viewModel: CategoriesListViewModel = hiltViewModel(),
    onNavigateToAdd: ()->Unit,
    onNavigateToEdit: (Long)->Unit,
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

    CategoriesListContents(
        snackbarHostState = snackbarHostState,
        categories = categories,
        loadIsCompleted = loadIsCompleted,
        onAdd = onNavigateToAdd,
        onEdit = onNavigateToEdit,
        onBack = onBack
    )
}

@Composable
fun CategoriesListContents(
    snackbarHostState: SnackbarHostState ?= null,
    categories: List<Category>,
    loadIsCompleted: Boolean,
    onAdd: () -> Unit,
    onEdit: (Long) -> Unit,
    onBack: () -> Unit,
){
    BaseScreen(
        title = "categories",
        snackbarHostState = snackbarHostState,
        showBackButton = true,
        onBack = onBack,
        bottomBar = {
            if (loadIsCompleted){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(
                        onClick = onAdd,
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = VanillaCream,
                            contentColor = Black
                        ),
                        contentPadding = PaddingValues(
                            horizontal = 20.dp,
                            vertical = 20.dp
                        )
                    ){
                        Text(
                            text = "add",
                            fontSize = 28.sp
                        )
                    }
                }
            }
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()){
                LazyColumnDark(
                    content = {
                        items(categories){category ->
                            CardLight(
                                onClick = { onEdit(category.id) },
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
fun CategoriesListPreview(){
    val categories = listOf(
        Category(1, "Whiskey"),
        Category(2, "Gin"),
        Category(3, "Vodka"),
    )

    CategoriesListContents(
        categories = categories,
        loadIsCompleted = true,
        onAdd = {},
        onEdit = {},
        onBack = {}
    )
}
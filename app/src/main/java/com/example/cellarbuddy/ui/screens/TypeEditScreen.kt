package com.example.cellarbuddy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.cellarbuddy.infrastructure.UiEvent
import com.example.cellarbuddy.ui.components.BaseScreen
import com.example.cellarbuddy.ui.components.ButtonLight
import com.example.cellarbuddy.ui.components.InputTextField
import com.example.cellarbuddy.ui.components.TextSimple
import com.example.cellarbuddy.ui.components.WorkingIcon
import com.example.cellarbuddy.ui.theme.Black
import com.example.cellarbuddy.ui.theme.VanillaCream
import com.example.cellarbuddy.viewModels.TypeEditViewModel
import kotlinx.coroutines.flow.map

@Composable
fun TypeEditScreen(
    id: Long,
    viewModel: TypeEditViewModel = hiltViewModel(),
    onBack: ()->Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val name by viewModel.uiState.map { it.type?.name ?: "" }.collectAsState(initial = "")
    val synonyms by viewModel.uiState.map { it.type?.synonyms  ?: ""}.collectAsState(initial = "")
    val drinksCount by viewModel.uiState.map { it.drinksCount }.collectAsState(initial = 0)
    val categoriesCount by viewModel.uiState.map { it.categoriesCount }.collectAsState(initial = 0)
    val loadIsCompleted by viewModel.uiFlags.map { it.loadIsCompleted }.collectAsState(initial = true)

    // managing events sent by the viewModel
    LaunchedEffect(Unit) {
        viewModel.uiEvents.collect { event ->
            when (event){
                is UiEvent.ShowMessage -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                is UiEvent.SuccessUpdate -> {
                    onBack()
                }
                is UiEvent.SuccessDelete -> {
                    onBack()
                }
                is UiEvent.Failed -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                else -> Unit // ignore any other case
            }
        }
    }

    // load the data once
    LaunchedEffect(Unit) {
        viewModel.loadOnce(id)
    }

    TypeEditContents(
        snackbarHostState = snackbarHostState,
        id = id,
        name = name,
        synonyms = synonyms,
        drinksCount = drinksCount,
        categoriesCount = categoriesCount,
        loadIsCompleted = loadIsCompleted,
        onSave = viewModel::updateType,
        onDelete = viewModel::deleteType,
        onNameChange = viewModel::setName,
        onSynonymsChange = viewModel::setSynonyms,
        onBack = onBack
    )
}

@Composable
fun TypeEditContents(
    snackbarHostState: SnackbarHostState ?= null,
    id: Long,
    name: String,
    synonyms: String,
    drinksCount: Int,
    categoriesCount: Int,
    loadIsCompleted: Boolean,
    onNameChange: (String) -> Unit,
    onSynonymsChange: (String) -> Unit,
    onSave: () -> Unit,
    onDelete: () -> Unit,
    onBack: () -> Unit,
){
    BaseScreen(
        title = "edit type",
        snackbarHostState = snackbarHostState,
        showBackButton = true,
        onBack = onBack,
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                ButtonLight(
                    text = "save",
                    onClick = onSave
                )

                Spacer(modifier = Modifier.width(8.dp))

                ButtonLight(
                    text = "delete",
                    onClick = onDelete
                )
            }
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Black.copy(alpha = 0.7f))
                        .padding(10.dp)
                ) {
                    val verticalSpaceModifier = Modifier.height(30.dp)

                    TextSimple(text = "id", color = VanillaCream)
                    InputTextField(
                        value = id.toString(),
                        onValueChange = { },
                        readOnly = true
                    )

                    Spacer(modifier = verticalSpaceModifier)

                    TextSimple(text = "name", color = VanillaCream)
                    InputTextField(
                        value = name,
                        onValueChange = onNameChange
                    )

                    Spacer(modifier = verticalSpaceModifier)

                    TextSimple(text = "synonyms", color = VanillaCream)
                    InputTextField(
                        value = synonyms,
                        onValueChange = onSynonymsChange
                    )

                    Spacer(modifier = verticalSpaceModifier)

                    TextSimple(text = "drinks", color = VanillaCream)
                    InputTextField(
                        value = drinksCount.toString(),
                        onValueChange = { },
                        readOnly = true
                    )

                    Spacer(modifier = verticalSpaceModifier)

                    TextSimple(text = "categories", color = VanillaCream)
                    InputTextField(
                        value = categoriesCount.toString(),
                        onValueChange = { },
                        readOnly = true
                    )
                }

                if (!loadIsCompleted) {
                    WorkingIcon()
                }
            }
        }
    )
}

@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
@Composable
fun TypesEditPreview(){
    TypeEditContents(
        id = 1L,
        name = "white",
        synonyms = "lefko lefko1",
        drinksCount = 5,
        categoriesCount = 15,
        loadIsCompleted = true,
        onNameChange = {},
        onSynonymsChange = {},
        onSave = {},
        onDelete = {},
        onBack = {},
    )
}
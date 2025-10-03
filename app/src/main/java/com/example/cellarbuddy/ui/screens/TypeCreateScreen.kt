package com.example.cellarbuddy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.cellarbuddy.infrastructure.UiEvent
import com.example.cellarbuddy.ui.components.BaseScreen
import com.example.cellarbuddy.ui.components.ButtonLight
import com.example.cellarbuddy.ui.components.InputTextField
import com.example.cellarbuddy.ui.components.TextSimple
import com.example.cellarbuddy.ui.theme.Black
import com.example.cellarbuddy.ui.theme.VanillaCream
import com.example.cellarbuddy.ui.theme.White
import com.example.cellarbuddy.viewModels.TypeCreateViewModel
import kotlinx.coroutines.flow.map

@Composable
fun TypeCreateScreen(
    viewModel: TypeCreateViewModel = hiltViewModel(),
    onBack: ()->Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val name by viewModel.uiState.map { it.name }.collectAsState(initial = "")
    val synonyms by viewModel.uiState.map { it.synonyms }.collectAsState(initial = "")

    // managing events sent by the viewModel
    LaunchedEffect(Unit) {
        viewModel.uiEvents.collect { event ->
            when (event){
                is UiEvent.ShowMessage -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                is UiEvent.Success -> {
                    onBack()
                }
                is UiEvent.Failed -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                else -> Unit // ignore any other case
            }
        }
    }

    TypeCreateContents(
        snackbarHostState = snackbarHostState,
        name = name,
        synonyms = synonyms,
        onSave = viewModel::createType,
        onNameChange = viewModel::setName,
        onSynonymsChange = viewModel::setSynonyms,
        onBack = onBack
    )
}

@Composable
fun TypeCreateContents(
    snackbarHostState: SnackbarHostState ?= null,
    name: String,
    synonyms: String,
    onNameChange: (String) -> Unit,
    onSynonymsChange: (String) -> Unit,
    onSave: () -> Unit,
    onBack: () -> Unit,
){
    BaseScreen(
        title = "types",
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
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Black.copy(alpha = 0.7f))
                    .padding(10.dp)
            ){
                val verticalSpaceModifier = Modifier.height(30.dp)

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
            }
        }
    )

}

@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
@Composable
fun TypesCreatePreview(){
    TypeCreateContents(
        name = "white",
        synonyms = "lefko",
        onNameChange = {},
        onSynonymsChange = {},
        onSave = {},
        onBack = {},
    )
}
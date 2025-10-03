package com.example.cellarbuddy.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cellarbuddy.data.models.Category
import com.example.cellarbuddy.data.repositories.CategoryRepository
import com.example.cellarbuddy.infrastructure.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CategoriesListUiState(
    val categories: List<Category> = emptyList()
)

data class CategoriesListUiFlags(
    // true when the current loading operation has finished
    val loadIsCompleted: Boolean = true
)

@HiltViewModel
class CategoriesListViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel() {

    // flag to ensure the data is loaded only once on initialization
    // Not the same as loadIsCompleted that can be executed multiple times, for example on a refresh
    private var hasLoaded = false

    private val _uiState = MutableStateFlow(CategoriesListUiState())
    val uiState: StateFlow<CategoriesListUiState> = _uiState.asStateFlow()

    private val _uiFlags = MutableStateFlow(CategoriesListUiFlags())
    val uiFlags: StateFlow<CategoriesListUiFlags> = _uiFlags.asStateFlow()

    private val _uiEvents = MutableSharedFlow<UiEvent>()
    val uiEvents = _uiEvents.asSharedFlow()

    fun loadOnce(){
        if (!hasLoaded){
            hasLoaded = true

            viewModelScope.launch {
                _uiFlags.update { it.copy(loadIsCompleted = false) }

                try{
                    repository.getCategories().collect{ categoriesList ->
                        val sortedCategories = categoriesList.sortedBy {it.name.lowercase()}
                        _uiState.value = CategoriesListUiState(categories = sortedCategories)
                        _uiFlags.update { it.copy(loadIsCompleted = true) }
                    }
                }
                catch (ex: Exception){
                    _uiEvents.emit(UiEvent.Failed("Error: ${ex.localizedMessage}"))
                    _uiFlags.update { it.copy(loadIsCompleted = true) }
                }
            }
        }
    }

}
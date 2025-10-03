package com.example.cellarbuddy.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cellarbuddy.data.models.Type
import com.example.cellarbuddy.data.repositories.TypeRepository
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

data class TypeCreateUiState(
    val name: String = "",
    val synonyms: String = ""
)

@HiltViewModel
class TypeCreateViewModel @Inject constructor(
    private val repository: TypeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TypeCreateUiState())
    val uiState: StateFlow<TypeCreateUiState> = _uiState.asStateFlow()

    private val _uiEvents = MutableSharedFlow<UiEvent>()
    val uiEvents = _uiEvents.asSharedFlow()

    fun setName(value: String){
        _uiState.update { it.copy(name = value) }
    }

    fun setSynonyms(value: String){
        _uiState.update { it.copy(synonyms = value) }
    }

    private suspend fun validate(): Boolean{
        val current = _uiState.value
        var success = true

        if (current.name.isBlank()){
            _uiEvents.emit(UiEvent.ShowMessage("The name is required."))
            success = false
        }

        return success
    }

    fun createType(){
        viewModelScope.launch{
            if (validate()){
                val current = _uiState.value
                val type = Type(
                    name = current.name,
                    synonyms = current.synonyms,
                )
                try{
                    repository.insertType(type)
                    _uiEvents.emit(UiEvent.Success("The type was created"))
                }
                catch (e: Exception){
                    _uiEvents.emit(UiEvent.Failed("Error: ${e.localizedMessage}"))
                }
            }
        }
    }
}
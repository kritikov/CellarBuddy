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

data class TypeEditUiState(
    val type: Type? = null,
    val drinksCount: Int = 0,
    val categoriesCount: Int = 0,
)

data class TypeEditUiFlags(
    // true when the current loading operation has finished
    val loadIsCompleted: Boolean = true
)

@HiltViewModel
class TypeEditViewModel @Inject constructor(
    private val repository: TypeRepository
) : ViewModel() {

    // flag to ensure the data is loaded only once on initialization
    // Not the same as loadIsCompleted that can be executed multiple times, for example on a refresh
    private var hasLoaded = false

    private val _uiState = MutableStateFlow(TypeEditUiState())
    val uiState: StateFlow<TypeEditUiState> = _uiState.asStateFlow()

    private val _uiFlags = MutableStateFlow(TypeEditUiFlags())
    val uiFlags: StateFlow<TypeEditUiFlags> = _uiFlags.asStateFlow()

    private val _uiEvents = MutableSharedFlow<UiEvent>()
    val uiEvents = _uiEvents.asSharedFlow()

    fun loadOnce(typeId: Long){
        if (!hasLoaded){
            hasLoaded = true

            viewModelScope.launch {
                _uiFlags.update { it.copy(loadIsCompleted = false) }

                try{
                    val type = repository.getType(typeId)
                    val drinksCount = repository.getDrinksCountForType(typeId)
                    val categoriesCount = repository.getCategoriesCountForType(typeId)
                    _uiState.value = TypeEditUiState(
                        type = type,
                        drinksCount = drinksCount,
                        categoriesCount = categoriesCount,
                    )
                    _uiFlags.update { it.copy(loadIsCompleted = true) }
                }
                catch (ex: Exception){
                    _uiEvents.emit(UiEvent.Failed("Error: ${ex.localizedMessage}"))
                    _uiFlags.update { it.copy(loadIsCompleted = true) }
                }
            }
        }
    }


    fun setName(value: String){
        val current = _uiState.value
        val updatedType = current.type?.copy(name = value)
        _uiState.value = current.copy(type = updatedType)
    }

    fun setSynonyms(value: String){
        val current = _uiState.value
        val updatedType = current.type?.copy(synonyms = value)
        _uiState.value = current.copy(type = updatedType)
    }

    private suspend fun validate(): Boolean{
        val current = _uiState.value
        var success = true

        if (current.type!!.name.isBlank()){
            _uiEvents.emit(UiEvent.ShowMessage("The name is required."))
            success = false
        }

        return success
    }

    fun updateType(){
        viewModelScope.launch{
            if (validate()){
                val current = _uiState.value

                current.type?.let{ type ->
                    try{
                        repository.updateType(type)
                        _uiEvents.emit(UiEvent.SuccessUpdate("The type was updated"))
                    }
                    catch (e: Exception){
                        _uiEvents.emit(UiEvent.Failed("Error: ${e.localizedMessage}"))
                    }
                }
            }
        }
    }

    fun deleteType(){
        val type = _uiState.value.type ?: return
        viewModelScope.launch{
            try{
                val hasDrinks = repository.hasDrinks(type.id)
                if (!hasDrinks){
                    repository.deleteType(type.id)
                    _uiEvents.emit(UiEvent.SuccessDelete("The type was deleted"))
                }
                else{
                    _uiEvents.emit(UiEvent.Failed("You cannot delete the type because there are drinks that use it"))
                }

            }
            catch (e: Exception){
                _uiEvents.emit(UiEvent.Failed("Error: ${e.localizedMessage}"))
            }
        }
    }
}
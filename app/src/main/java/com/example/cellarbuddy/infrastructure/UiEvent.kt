package com.example.cellarbuddy.infrastructure

sealed class UiEvent {
    data class ShowMessage(val message: String) : UiEvent()
    data class Success(val message: String) : UiEvent()
    data class SuccessUpdate(val message: String) : UiEvent()
    data class SuccessDelete(val message: String) : UiEvent()
    data class Failed(val message: String) : UiEvent()
}
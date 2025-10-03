package com.example.cellarbuddy.infrastructure

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {

    @Serializable
    object Main: Screen

    @Serializable
    object TypesList: Screen

    @Serializable
    object TypeCreate: Screen

    @Serializable
    data class TypeEdit(val id: Long): Screen

    @Serializable
    object CategoriesList: Screen

    @Serializable
    object DrinkCategoriesList: Screen

}
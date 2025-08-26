package com.example.cellarbuddy.infrastructure

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {

    @Serializable
    object Main: Screen

}
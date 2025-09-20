package com.example.cellarbuddy.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Drinks")

data class Drink (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val categoryId: Long,
    val imagePath: String? = null,
    val rating: Int? = null,
    val producer: String? = null,
    val comments: String? = null,
    val bottlingYear: Int? = null,
)
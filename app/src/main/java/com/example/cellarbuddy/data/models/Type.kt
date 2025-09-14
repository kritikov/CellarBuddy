package com.example.cellarbuddy.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Types"
)
data class Type (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var name: String,
    var synonyms: String,
)
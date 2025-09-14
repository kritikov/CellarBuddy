package com.example.cellarbuddy.data.models

import androidx.room.Entity

@Entity(
    tableName = "CategoryTypeCrossRef",
    primaryKeys = ["categoryId", "typeId"]
)
data class CategoryTypeCrossRef (
    val categoryId: Long,
    val typeId: Long
)

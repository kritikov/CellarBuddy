package com.example.cellarbuddy.data.models

import androidx.room.Entity

@Entity(
    tableName = "DrinkTypeCrossRef",
    primaryKeys = ["drinkId", "typeId"]
)
data class DrinkTypeCrossRef (
    val drinkId: Long,
    val typeId: Long
)
package com.example.cellarbuddy.data.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class DrinkWithRelations (
    @Embedded val drink: Drink,

    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id",
    )
    val category: Category?,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = DrinkTypeCrossRef::class,
            parentColumn = "drinkId",
            entityColumn = "typeId"
        )
    )
    var types: List<Type>
)
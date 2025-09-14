package com.example.cellarbuddy.data.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CategoryWithRelations (
    @Embedded val category: Category,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = CategoryTypeCrossRef::class,
            parentColumn = "categoryId",
            entityColumn = "typeId"
        )
    )
    val types: List<Type>
)
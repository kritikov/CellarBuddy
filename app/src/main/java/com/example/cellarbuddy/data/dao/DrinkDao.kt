package com.example.cellarbuddy.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.cellarbuddy.data.models.Drink
import com.example.cellarbuddy.data.models.DrinkTypeCrossRef
import com.example.cellarbuddy.data.models.DrinkWithRelations

@Dao
interface DrinkDao {

    @Query("SELECT * FROM Drinks WHERE id = :id")
    suspend fun getDrink(id: Long): Drink?

    @Transaction
    @Query("SELECT * FROM Drinks WHERE id = :id")
    suspend fun getDrinkWithRelations(id: Long): DrinkWithRelations?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrinks(drinks: List<Drink>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrinkTypeCrossRefs(refs: List<DrinkTypeCrossRef>)
}
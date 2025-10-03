package com.example.cellarbuddy.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.cellarbuddy.data.models.Type
import kotlinx.coroutines.flow.Flow

@Dao
interface TypeDao {

    @Query("SELECT * FROM Types WHERE id = :id")
    suspend fun getType(id: Long): Type?

    @Query("SELECT * FROM Types")
    fun getTypes(): Flow<List<Type>>

    @Query("SELECT COUNT(*) FROM DrinkTypeCrossRef WHERE typeId = :typeId")
    suspend fun getDrinksCountForType(typeId: Long): Int

    @Query("SELECT COUNT(*) FROM CategoryTypeCrossRef WHERE typeId = :typeId")
    suspend fun getCategoriesCountForType(typeId: Long): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTypes(types: List<Type>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertType(type: Type)

    @Update
    suspend fun updateType(type: Type)

    @Query("DELETE FROM types WHERE id = :typeId")
    suspend fun deleteType(typeId: Long)

}
package com.example.cellarbuddy.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cellarbuddy.data.models.Type

@Dao
interface TypeDao {

    @Query("SELECT * FROM Types WHERE id = :id")
    suspend fun getType(id: Long): Type?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTypes(types: List<Type>)

}
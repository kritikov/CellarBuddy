package com.example.cellarbuddy.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.cellarbuddy.data.models.Category
import com.example.cellarbuddy.data.models.CategoryTypeCrossRef
import com.example.cellarbuddy.data.models.CategoryWithRelations
import com.example.cellarbuddy.data.models.Type
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM Categories WHERE id = :id")
    suspend fun getCategory(id: Long): Category?

    @Query("SELECT * FROM Categories")
    fun getCategories(): Flow<List<Category>>

    @Transaction
    @Query("SELECT * FROM Categories WHERE id = :id")
    suspend fun getCategoryWithRelations(id: Long): CategoryWithRelations?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<Category>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryTypeCrossRefs(refs: List<CategoryTypeCrossRef>)

}
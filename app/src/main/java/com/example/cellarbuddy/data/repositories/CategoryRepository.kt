package com.example.cellarbuddy.data.repositories

import com.example.cellarbuddy.data.dao.CategoryDao
import com.example.cellarbuddy.data.models.Category
import com.example.cellarbuddy.data.models.Type
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val dao: CategoryDao
) {
    suspend fun getCategory(id: Long) = dao.getCategory(id)

    suspend fun getCategoryWithRelations(id: Long) = dao.getCategoryWithRelations(id)

    fun getCategories(): Flow<List<Category>> = dao.getCategories()
}
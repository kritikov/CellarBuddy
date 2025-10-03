package com.example.cellarbuddy.data.repositories

import com.example.cellarbuddy.data.dao.TypeDao
import com.example.cellarbuddy.data.models.Type
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TypeRepository @Inject constructor(
    private val dao: TypeDao
){
    suspend fun getType(id: Long) = dao.getType(id)

    fun getTypes(): Flow<List<Type>> = dao.getTypes()

    suspend fun insertType(type: Type) = dao.insertType(type)

    suspend fun getDrinksCountForType(typeId: Long): Int = dao.getDrinksCountForType(typeId)
    suspend fun getCategoriesCountForType(typeId: Long): Int = dao.getCategoriesCountForType(typeId)

    suspend fun updateType(type: Type) = dao.updateType(type)

    suspend fun hasDrinks(typeId: Long): Boolean{
        return dao.getDrinksCountForType(typeId) > 0
    }

    suspend fun deleteType(typeId: Long) = dao.deleteType(typeId)
}
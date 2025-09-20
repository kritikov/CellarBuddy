package com.example.cellarbuddy.data.repositories

import com.example.cellarbuddy.data.dao.TypeDao
import javax.inject.Inject

class TypeRepository @Inject constructor(
    private val dao: TypeDao
){
    suspend fun getType(id: Long) = dao.getType(id)

}
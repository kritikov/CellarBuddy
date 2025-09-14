package com.example.cellarbuddy.data.repositories

import com.example.cellarbuddy.data.dao.DrinkDao
import javax.inject.Inject

class DrinkRepository @Inject constructor(
    private val dao: DrinkDao
){
    suspend fun getDrink(id: Long) = dao.getDrink(id)
    suspend fun getDrinkWithRelations(id: Long) = dao.getDrinkWithRelations(id)

}
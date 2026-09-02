package com.example.quickbite.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomMealDao {
    @Insert
    suspend fun insert(meal: CustomMeal)

    @Query("SELECT * FROM custom_meals ORDER BY id DESC")
    fun getAll(): Flow<List<CustomMeal>>
}
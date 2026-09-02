package com.example.quickbite.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MealChoiceDao {
    @Insert
    suspend fun insert(choice: MealChoice)

    @Query("SELECT * FROM meal_choices ORDER BY timestamp DESC")
    fun getAllChoices(): Flow<List<MealChoice>>
}

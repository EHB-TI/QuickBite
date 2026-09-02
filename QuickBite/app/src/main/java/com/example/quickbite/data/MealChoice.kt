package com.example.quickbite.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_choices")
data class MealChoice(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val mealId: Int,
    val mealName: String,
    val timestamp: Long
)

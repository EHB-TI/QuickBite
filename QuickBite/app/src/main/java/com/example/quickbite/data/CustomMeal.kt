package com.example.quickbite.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "custom_meals")
data class CustomMeal(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val emoji: String,
    val category: String
)
package com.example.quickbite.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf

class MealRepository(
    private val choiceDao: MealChoiceDao,
    private val customMealDao: CustomMealDao
) {

    val history: Flow<List<MealChoice>> = choiceDao.getAllChoices()

    val allMeals: Flow<List<Meal>> = customMealDao.getAll().combine(flowOf(MealCatalog.meals)) { custom, catalog ->
        val customMapped = custom.map {
            Meal(id = (1000 + it.id).toInt(), name = it.name, emoji = it.emoji, category = it.category)
        }
        catalog + customMapped
    }

    suspend fun recordChoice(meal: Meal) {
        choiceDao.insert(
            MealChoice(mealId = meal.id, mealName = meal.name, timestamp = System.currentTimeMillis())
        )
    }

    suspend fun addCustomMeal(name: String, emoji: String, category: String) {
        customMealDao.insert(CustomMeal(name = name, emoji = emoji, category = category))
    }
}
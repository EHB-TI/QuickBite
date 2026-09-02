package com.example.quickbite.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickbite.data.AppDatabase
import com.example.quickbite.data.Meal
import com.example.quickbite.data.MealChoice
import com.example.quickbite.data.MealRepository
import com.example.quickbite.utils.filterAvailableMeals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MealViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getInstance(application)
    private val repository = MealRepository(
        choiceDao = database.mealChoiceDao(),
        customMealDao = database.customMealDao()
    )

    private val skippedIds = MutableStateFlow<Set<Int>>(emptySet())

    val history: StateFlow<List<MealChoice>> = repository.history
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val availableMeals: StateFlow<List<Meal>> = combine(
        repository.allMeals,
        repository.history,
        skippedIds
    ) { catalog, history, skipped ->
        filterAvailableMeals(catalog, history)
            .filterNot { it.id in skipped }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val stats: StateFlow<List<Pair<String, Int>>> = repository.history
        .map { list ->
            list.groupBy { it.mealName }
                .map { (name, choices) -> name to choices.size }
                .sortedByDescending { it.second }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun choose(meal: Meal) {
        viewModelScope.launch { repository.recordChoice(meal) }
    }

    fun skip(meal: Meal) {
        skippedIds.value = skippedIds.value + meal.id
    }

    fun addCustomMeal(name: String, emoji: String, category: String) {
        viewModelScope.launch { repository.addCustomMeal(name, emoji, category) }
    }
}
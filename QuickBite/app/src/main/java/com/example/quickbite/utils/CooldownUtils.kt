package com.example.quickbite.utils

import com.example.quickbite.data.Meal
import com.example.quickbite.data.MealChoice

private const val COOLDOWN_DAYS = 3
private const val DAY_MS = 24 * 60 * 60 * 1000L

/**
 * This is the app's USP logic: filters out meals chosen within the
 * cooldown window so the same suggestion doesn't reappear too soon.
 * Falls back to the full catalog if the cooldown would leave nothing
 * to suggest, so the user is never stuck with an empty deck.
 */
fun filterAvailableMeals(catalog: List<Meal>, history: List<MealChoice>): List<Meal> {
    val cooldownCutoff = System.currentTimeMillis() - COOLDOWN_DAYS * DAY_MS
    val recentMealIds = history
        .filter { it.timestamp >= cooldownCutoff }
        .map { it.mealId }
        .toSet()

    val available = catalog.filter { it.id !in recentMealIds }
    return available.ifEmpty { catalog }
}

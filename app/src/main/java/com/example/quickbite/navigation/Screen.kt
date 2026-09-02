package com.example.quickbite.navigation

sealed class Screen(val route: String, val label: String) {
    data object Swipe : Screen("swipe", "Choisir")
    data object History : Screen("history", "Historique")
    data object Stats : Screen("stats", "Stats")
    data object AddMeal : Screen("add_meal", "Ajouter")
}
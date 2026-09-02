package com.example.quickbite.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quickbite.ui.screens.AddMealScreen
import com.example.quickbite.ui.screens.HistoryScreen
import com.example.quickbite.ui.screens.StatsScreen
import com.example.quickbite.ui.screens.SwipeScreen
import com.example.quickbite.viewmodel.MealViewModel

@Composable
fun QuickBiteNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: MealViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Swipe.route,
        modifier = modifier
    ) {
        composable(Screen.Swipe.route) {
            SwipeScreen(
                viewModel = viewModel,
                onAddMeal = { navController.navigate(Screen.AddMeal.route) }
            )
        }
        composable(Screen.History.route) { HistoryScreen(viewModel) }
        composable(Screen.Stats.route) { StatsScreen(viewModel) }
        composable(Screen.AddMeal.route) {
            AddMealScreen(
                onSave = { name, emoji, category ->
                    viewModel.addCustomMeal(name, emoji, category)
                    navController.popBackStack()
                },
                onCancel = { navController.popBackStack() }
            )
        }
    }
}
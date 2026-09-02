package com.example.quickbite.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quickbite.ui.components.SwipeableMealCard
import com.example.quickbite.viewmodel.MealViewModel

@Composable
fun SwipeScreen(viewModel: MealViewModel, onAddMeal: () -> Unit) {
    val meals by viewModel.availableMeals.collectAsState()
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    val categories = remember(meals) { meals.map { it.category }.distinct() }
    val filteredMeals = remember(meals, selectedCategory) {
        if (selectedCategory == null) meals else meals.filter { it.category == selectedCategory }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Qu'est-ce qu'on mange ?", style = MaterialTheme.typography.headlineMedium)
            IconButton(onClick = onAddMeal) {
                Icon(Icons.Filled.Add, contentDescription = "Ajouter un plat")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Glisse à droite pour choisir, à gauche pour passer",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            item {
                FilterChip(
                    selected = selectedCategory == null,
                    onClick = { selectedCategory = null },
                    label = { Text("Tous") }
                )
            }
            items(categories) { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category },
                    label = { Text(category) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            val meal = filteredMeals.firstOrNull()
            if (meal != null) {
                SwipeableMealCard(
                    meal = meal,
                    onSwipeRight = { viewModel.choose(it) },
                    onSwipeLeft = { viewModel.skip(it) },
                    modifier = Modifier.fillMaxWidth(0.85f)
                )
            } else {
                Text("Plus de suggestions pour l'instant, reviens plus tard !")
            }
        }
    }
}
package com.example.quickbite.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quickbite.ui.components.StatBar
import com.example.quickbite.viewmodel.MealViewModel

@Composable
fun StatsScreen(viewModel: MealViewModel) {
    val stats by viewModel.stats.collectAsState()

    if (stats.isEmpty()) {
        Text(
            text = "Pas encore de statistiques, commence à choisir des plats !",
            modifier = Modifier.padding(24.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        return
    }

    val maxCount = stats.maxOf { it.second }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(vertical = 16.dp)) {
        items(stats) { (name, count) -> StatBar(name = name, count = count, maxCount = maxCount) }
    }
}

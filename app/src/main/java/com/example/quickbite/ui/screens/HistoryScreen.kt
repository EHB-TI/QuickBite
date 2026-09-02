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
import com.example.quickbite.ui.components.HistoryRow
import com.example.quickbite.viewmodel.MealViewModel

@Composable
fun HistoryScreen(viewModel: MealViewModel) {
    val history by viewModel.history.collectAsState()

    if (history.isEmpty()) {
        Text(
            text = "Aucun choix pour l'instant.",
            modifier = Modifier.padding(24.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        return
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(history) { choice -> HistoryRow(choice) }
    }
}

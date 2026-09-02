package com.example.quickbite.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickbite.data.Meal
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

private const val SWIPE_THRESHOLD = 300f

/**
 * A draggable card representing one meal suggestion.
 * Swipe right = choose it, swipe left = skip it.
 */
@Composable
fun SwipeableMealCard(
    meal: Meal,
    onSwipeRight: (Meal) -> Unit,
    onSwipeLeft: (Meal) -> Unit,
    modifier: Modifier = Modifier
) {
    val offsetX = remember(meal.id) { Animatable(0f) }
    val scope = rememberCoroutineScope()

    Card(
        modifier = modifier
            .offset { IntOffset(offsetX.value.roundToInt(), 0) }
            .pointerInput(meal.id) {
                detectDragGestures(
                    onDragEnd = {
                        scope.launch {
                            when {
                                offsetX.value > SWIPE_THRESHOLD -> onSwipeRight(meal)
                                offsetX.value < -SWIPE_THRESHOLD -> onSwipeLeft(meal)
                                else -> offsetX.animateTo(0f)
                            }
                        }
                    }
                ) { change, dragAmount ->
                    change.consume()
                    scope.launch { offsetX.snapTo(offsetX.value + dragAmount.x) }
                }
            },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.75f)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = meal.emoji, fontSize = 96.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = meal.name, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = meal.category, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

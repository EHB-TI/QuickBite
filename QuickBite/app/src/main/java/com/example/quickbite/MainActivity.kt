package com.example.quickbite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.quickbite.navigation.QuickBiteNavGraph
import com.example.quickbite.ui.components.BottomNavBar
import com.example.quickbite.ui.theme.QuickBiteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickBiteTheme {
                QuickBiteApp()
            }
        }
    }
}

@Composable
fun QuickBiteApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { padding ->
        QuickBiteNavGraph(
            navController = navController,
            modifier = Modifier.padding(padding)
        )
    }
}

package com.example.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment.ui.theme.AssignmentTheme

class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AssignmentTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = NavPath.Main.name) {
                    composable(NavPath.Main.name){
                        HomeScreen(
                            viewModel = viewModel,
                            onSearchBarClick = { navController.navigate(NavPath.Search.name) }
                        )
                    }
                    composable(NavPath.Search.name){
                        SearchScreen(
                            viewModel = viewModel,
                            onBackButtonClick = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}

enum class NavPath {
    Main, Search
}
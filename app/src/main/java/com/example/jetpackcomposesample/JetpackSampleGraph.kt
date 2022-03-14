package com.example.jetpackcomposesample

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposesample.presentation.animation.AnimationRoute
import com.example.jetpackcomposesample.presentation.home.HomeRoute


@Composable
fun JetpackNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navigationToAnimation: () -> Unit = {},
    startDestination: String = JetpackSampleDestinations.HOME_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(JetpackSampleDestinations.HOME_ROUTE) {
            HomeRoute(
                navigationToAnimation,
            )
        }
        composable(JetpackSampleDestinations.ANIMATION_ROUTE) {
            AnimationRoute()
        }
    }
}

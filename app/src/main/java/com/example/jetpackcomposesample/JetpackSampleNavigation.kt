package com.example.jetpackcomposesample

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object JetpackSampleDestinations {
    const val HOME_ROUTE = "home"
    const val ANIMATION_ROUTE = "animation"
}

/**
 * Models the navigation actions in the app.
 */
class JetpackSampleNavigation(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(JetpackSampleDestinations.HOME_ROUTE) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
    val navigateToAnimation: () -> Unit = {
        navController.navigate(JetpackSampleDestinations.ANIMATION_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

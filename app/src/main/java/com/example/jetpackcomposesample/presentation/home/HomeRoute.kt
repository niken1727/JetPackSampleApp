package com.example.jetpackcomposesample.presentation.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposesample.JetpackSampleNavigation
import com.example.jetpackcomposesample.R


/**
 * Stateful composable that displays the Navigation route for the Interests screen.
 *
 */
@Composable
fun HomeRoute(
    navigateToAnimation : () -> Unit,
) {
    HomeScreen(
        navigateToAnimation = navigateToAnimation,
    )
}



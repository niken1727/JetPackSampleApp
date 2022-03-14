package com.example.jetpackcomposesample.presentation.animation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposesample.R

@Composable
fun AnimationScreen() {
    CardView()
}


@Composable
fun CardView() {
    var rotate by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (rotate) 180F else 0F,
        animationSpec = tween(800)
    )
    Column(
    ) {

        Card(
            Modifier
                .height(240.dp)
                .clickable {
                    rotate = !rotate
                }
                .graphicsLayer {
                    rotationY = rotationAngle
                    cameraDistance = 8 * density
                }
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Color(18, 52, 64),

            ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    Modifier
                        .size(64.dp)
                        .height(64.dp),
                    shape = CircleShape,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.post_2),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
                Text(
                    text = "Niken Maharjan",
                    Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp),
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

        }

    }

}

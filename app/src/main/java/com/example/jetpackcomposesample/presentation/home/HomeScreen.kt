package com.example.jetpackcomposesample.presentation.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposesample.R

@Composable
fun HomeScreen(
    navigateToAnimation: () -> Unit,
) {
    AddFeature(navigateToAnimation)
}


@Composable
fun AddFeature(
    navigateToAnimation: () -> Unit
) {
    val numbers = (0..6).toList()
    Column {
        LazyRow(
            Modifier.padding(start = 4.dp, top = 16.dp)
        ) {
            items(numbers.size) {
                CircleImageViewExample()
            }
        }
        FeatureView(navigateToAnimation)
        FABButtonExample()
    }

}


fun showToast(context: Context, selectedItem: Int) {
    Toast.makeText(context, selectedItem.toString(), Toast.LENGTH_SHORT).show()
}

@Composable
fun FABButtonExample() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(end = 16.dp, bottom = 64.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { /*TODO*/ },
            backgroundColor = Color.DarkGray
        ) {
            IconButton(
                onClick = { /*TODO*/ },
            ) {
                Icon(Icons.Filled.Add, "", tint = Color.White)
            }

        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeatureView(
    navigateToAnimation: () -> Unit
) {
    val cardList = listOf("Bike Animation", "Flip Animation")
    LazyVerticalGrid(
        cells = GridCells.Fixed(2)
    ) {
        items(cardList.size) { position ->
            Card(
                Modifier
                    .size(148.dp)
                    .padding(start = 16.dp, top = 24.dp, end = 16.dp)
                    .clickable {
                        navigateToAnimation()
                    },
                shape = RoundedCornerShape(16.dp),
                backgroundColor = Color(18, 52, 64),

                ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = cardList[position],
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }

            }
        }
    }


}


@Composable
fun CircleImageViewExample() {
    Card(
        Modifier
            .size(96.dp)
            .padding(8.dp),
        shape = CircleShape
    ) {
        Image(
            painter = painterResource(id = R.drawable.post_2),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}


package com.example.jetpackcomposesample

import android.content.Context
import android.graphics.fonts.Font
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposesample.model.BottomNavItem
import com.example.jetpackcomposesample.presentation.home.CircleImageViewExample
import com.example.jetpackcomposesample.presentation.home.showToast
import com.example.jetpackcomposesample.ui.theme.JetpackComposeSampleTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@Composable
fun JetpackSampleApp(
    windowSize: WindowSize
) {
    JetpackComposeSampleTheme {
        val systemUiController = rememberSystemUiController()
        val darkIcons = MaterialTheme.colors.isLight
        SideEffect {
            systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
        }
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            JetpackSampleNavigation(navController)
        }


        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: JetpackSampleDestinations.HOME_ROUTE


        TopBarExample(windowSize, navController, navigationActions.navigateToAnimation)
        BottomNavigation(
            navigateToHome = navigationActions.navigateToHome,
            navigateToAnimation = navigationActions.navigateToAnimation,
        )
    }

}


@Composable
fun TopBarExample(
    windowSize: WindowSize,
    navController: NavHostController,
    navigateToAnimation: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val isExpandedScreen = windowSize == WindowSize.Expanded
    val sizeAwareDrawerState = rememberSizeAwareDrawerState(isExpandedScreen)
    Box() {
        ModalDrawer(
            modifier = Modifier.fillMaxSize(),
            drawerContent = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircleImageViewExample()
                    Text(text = "JetPack Sample App")
                }
            },
            drawerState = sizeAwareDrawerState,
            // Only enable opening the drawer via gestures if the screen is not expanded
            gesturesEnabled = !isExpandedScreen
        ) {
            Scaffold(
                topBar = {
                    TopAppBar { coroutineScope.launch { sizeAwareDrawerState.open() } }
                }
            ) {
                JetpackNavGraph(
                    navController = navController,
                    navigationToAnimation = { navigateToAnimation() }

                )
            }
        }
    }
}

fun showClickActionToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


@Composable
fun TopAppBar(
    openDrawer: () -> Unit
){
    val context = LocalContext.current
    TopAppBar(
        title = { Text(text = "JetPack Sample App") },
        backgroundColor = Color.Black,
        contentColor = Color.White,
        navigationIcon = {
            IconButton(
                onClick = {
                    openDrawer()
                }) {
                Icon(Icons.Default.Menu, "")
            }
        },
        actions = {
            IconButton(onClick = {
                showClickActionToast(context, "Hello world")
            }) {
                Icon(Icons.Default.Help, "")
            }
        }
    )
}


@Composable
fun BottomNavigation(
    navigateToHome: () -> Unit,
    navigateToAnimation: () -> Unit
) {
    var selectedItem by remember { mutableStateOf(0) }
    val context = LocalContext.current

    val items = listOf<BottomNavItem>(
        BottomNavItem(Icons.Default.Home, "Home"),
        BottomNavItem(Icons.Default.Menu, "Menu"),
        BottomNavItem(Icons.Default.Person, "Profile")
    )
    Box(
        Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        BottomNavigation(
            backgroundColor = Color.DarkGray
        ) {
            items.forEachIndexed { index, item ->
                BottomNavigationItem(
                    icon = { Icon(item.icon, contentDescription = null, tint = Color.White) },
                    label = { Text(item.title, color = Color.White) },
                    selected = selectedItem == index,
                    onClick = {
                        @Composable
                        selectedItem = index
                        if (selectedItem == 1) {
                            navigateToAnimation()
                        } else {
                            navigateToHome()
                        }
                        showToast(context, selectedItem)
                    }
                )
            }
        }
    }

}


/**
 * Determine the drawer state to pass to the modal drawer.
 */
@Composable
private fun rememberSizeAwareDrawerState(isExpandedScreen: Boolean): DrawerState {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    return if (!isExpandedScreen) {
        // If we want to allow showing the drawer, we use a real, remembered drawer
        // state defined above
        drawerState
    } else {
        // If we don't want to allow the drawer to be shown, we provide a drawer state
        // that is locked closed. This is intentionally not remembered, because we
        // don't want to keep track of any changes and always keep it closed
        DrawerState(DrawerValue.Closed)
    }
}


@Composable
fun EditTextExample() {
    var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        label = { Text("Label") },
        isError = true
    )
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
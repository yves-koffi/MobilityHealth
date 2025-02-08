package fr.yjk.mobility.health.ui.screens.workspace

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.theme.MobilityHealthTheme
import kotlinx.serialization.Serializable

object WorkspaceMenu {
    @Serializable
    object Home

    @Serializable
    object Travel

    @Serializable
    object Category


    @Serializable
    object Help
}

@Composable
fun Workspace(modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()
    var selectedItem by rememberSaveable { mutableIntStateOf(1) }
    Scaffold(bottomBar = {
        Surface(
            modifier = Modifier
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .height(height = 72.dp),
            color = if (isSystemInDarkTheme()) Color.Black else Color.White,
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomNavigationBarItem.items.forEach { item ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = if (selectedItem == item.index) Arrangement.Center else Arrangement.Center,
                        modifier = Modifier
                            .clip(RoundedCornerShape(size = 32.dp))
                            .clickable {
                                selectedItem = item.index
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }

                            .weight(weight = if (selectedItem == item.index) 1.8f else 1f)
                            .height(height = 48.dp)
                            .background(
                                color = if (selectedItem == item.index) MaterialTheme.colorScheme.primary.copy(
                                    alpha = 0.1f
                                ) else Color.Unspecified
                            ),
                    ) {
                        Icon(
                            painter = painterResource(id = if (selectedItem == item.index) item.resourceIconUnselected else item.resourceIconSelected),
                            contentDescription = stringResource(id = item.resourceLabel),
                            modifier = Modifier.size(size = 32.dp),
                            tint = if (selectedItem == item.index) MaterialTheme.colorScheme.primary else Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(width = 10.dp))
                        if (selectedItem == item.index) {
                            Text(
                                stringResource(id = item.resourceLabel),
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }
                    /*NavigationBarItem(icon = {
                        Icon(
                            painter = painterResource(id = if (selectedItem == item.index) item.resourceIconUnselected else item.resourceIconSelected),
                            contentDescription = stringResource(id = item.resourceLabel)
                        )
                    },
                        alwaysShowLabel=false,
                        label = { Text(stringResource(id = item.resourceLabel)) },
                        selected = selectedItem == item.index,
                        onClick = {
                            selectedItem = item.index
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        })*/
                }
            }
        }
    }, floatingActionButton = {
       FloatingActionButton(shape = RoundedCornerShape(size = 72.dp),onClick = {}) {
            Image(
                painter = painterResource(R.drawable.alert01),
                contentDescription = null,
                modifier = Modifier.size(size = 32.dp)
            )
        }
    }) { innerPadding ->
        NavHost(
            modifier = modifier.padding(innerPadding),
            navController = navController,
            startDestination = WorkspaceMenu.Home
        ) {
            composable<WorkspaceMenu.Home> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Home", style = MaterialTheme.typography.titleLarge)
                }
            }
            composable<WorkspaceMenu.Travel> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Travel", style = MaterialTheme.typography.titleLarge)
                }
            }
            composable<WorkspaceMenu.Category> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Category", style = MaterialTheme.typography.titleLarge)
                }
            }
            composable<WorkspaceMenu.Help> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Help", style = MaterialTheme.typography.titleLarge)
                }
            }
        }
    }
}

data class BottomNavigationBarItem(
    val index: Int,
    val route: Any,
    @StringRes val resourceLabel: Int,
    @DrawableRes val resourceIconSelected: Int,
    @DrawableRes val resourceIconUnselected: Int,
) {
    companion object {
        val items: List<BottomNavigationBarItem> = listOf(
            BottomNavigationBarItem(
                index = 1,
                route = WorkspaceMenu.Home,
                resourceLabel = R.string.home,
                resourceIconSelected = R.drawable.home,
                resourceIconUnselected = R.drawable.home
            ),
            BottomNavigationBarItem(
                index = 2,
                route = WorkspaceMenu.Travel,
                resourceLabel = R.string.travel,
                resourceIconSelected = R.drawable.home,
                resourceIconUnselected = R.drawable.home
            ),
            BottomNavigationBarItem(
                index = 3,
                route = WorkspaceMenu.Category,
                resourceLabel = R.string.category,
                resourceIconSelected = R.drawable.home,
                resourceIconUnselected = R.drawable.home
            ),
            BottomNavigationBarItem(
                index = 4,
                route = WorkspaceMenu.Help,
                resourceLabel = R.string.home,
                resourceIconSelected = R.drawable.home,
                resourceIconUnselected = R.drawable.home
            ),
        )
    }
}

@Preview
@Composable
private fun WorkspacePreview() {
    MobilityHealthTheme {
        Workspace()
    }
}
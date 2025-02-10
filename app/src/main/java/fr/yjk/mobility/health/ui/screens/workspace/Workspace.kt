package fr.yjk.mobility.health.ui.screens.workspace

import WorkspaceTravel
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.yjk.mobility.health.R
import fr.yjk.mobility.health.ui.components.CustomNavigationBar
import fr.yjk.mobility.health.ui.components.CustomNavigationBarItem
import fr.yjk.mobility.health.ui.screens.workspace.partial.WorkspaceCategory
import fr.yjk.mobility.health.ui.screens.workspace.partial.WorkspaceHelp
import fr.yjk.mobility.health.ui.screens.workspace.partial.WorkspaceHome
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
fun Workspace(onSubscribe: () -> Unit) {
    val navController: NavHostController = rememberNavController()
    var selectedItem by rememberSaveable { mutableIntStateOf(1) }




    BackHandler {

    }


    Scaffold(contentWindowInsets = WindowInsets(top = 0), bottomBar = {
        Surface(
            modifier = Modifier
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                ),
            color = if (isSystemInDarkTheme()) Color.Black else Color.White,
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            CustomNavigationBar {
                BottomNavigationBarItem.items.forEach { item ->
                    CustomNavigationBarItem(index = selectedItem, item = item,
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
                    )
                }
            }
        }
    }, floatingActionButton = {
        FloatingActionButton(
            containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
            shape = RoundedCornerShape(size = 72.dp),
            onClick = {}) {
            Image(
                painter = painterResource(R.drawable.alert01),
                contentDescription = null,
                modifier = Modifier.size(size = 32.dp)
            )
        }
    }) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = WorkspaceMenu.Home
        ) {
            composable<WorkspaceMenu.Home> {
                WorkspaceHome(onSubscribe=onSubscribe)
            }
            composable<WorkspaceMenu.Travel> {
                WorkspaceTravel()
            }
            composable<WorkspaceMenu.Category> {
                WorkspaceCategory()
            }
            composable<WorkspaceMenu.Help> {
                WorkspaceHelp()
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
                resourceIconSelected = R.drawable.menu1,
                resourceIconUnselected = R.drawable.menu1
            ),
            BottomNavigationBarItem(
                index = 2,
                route = WorkspaceMenu.Travel,
                resourceLabel = R.string.travel,
                resourceIconSelected = R.drawable.menu2,
                resourceIconUnselected = R.drawable.menu2
            ),
            BottomNavigationBarItem(
                index = 3,
                route = WorkspaceMenu.Category,
                resourceLabel = R.string.category,
                resourceIconSelected = R.drawable.menu3,
                resourceIconUnselected = R.drawable.menu3
            ),
            BottomNavigationBarItem(
                index = 4,
                route = WorkspaceMenu.Help,
                resourceLabel = R.string.home,
                resourceIconSelected = R.drawable.menu4,
                resourceIconUnselected = R.drawable.menu4
            ),
        )
    }
}

@Preview
@Composable
private fun WorkspacePreview() {
    MobilityHealthTheme {
        Workspace(){

        }
    }
}
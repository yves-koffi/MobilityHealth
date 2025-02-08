package fr.yjk.mobility.health.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable


object Menu {
    @Serializable
    object Launch

    @Serializable
    object Read

    @Serializable
    object Write


    @Serializable
    object AuthKey
}

@Composable
fun MainAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Any = Menu.Read
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable<Menu.Read> {
            Greeting(name = "Setting")
        }
        composable<Menu.Write> {
            Greeting(name = "Setting")
        }
    }
}

@Composable
fun Greeting(
    name: String
) {



    Column {
        Text(
            text = "Hello Yves koffi !",
        )
    }

}
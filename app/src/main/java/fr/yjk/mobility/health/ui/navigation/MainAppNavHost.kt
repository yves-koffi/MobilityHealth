// Ce fichier configure l'hôte de navigation principal de l'application, définissant les routes de navigation et leurs écrans correspondants.


package fr.yjk.mobility.health.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import fr.yjk.mobility.health.localProvider.LocalPreferences
import fr.yjk.mobility.health.preferences.AuthState
import fr.yjk.mobility.health.ui.screens.BeforeAuthScreen
import fr.yjk.mobility.health.ui.screens.LaunchScreen
import fr.yjk.mobility.health.ui.screens.directory.MakeDirectory
import fr.yjk.mobility.health.ui.screens.login.LoginOtpScreen
import fr.yjk.mobility.health.ui.screens.login.LoginRequestScreen
import fr.yjk.mobility.health.ui.screens.question.MakeQuestion
import fr.yjk.mobility.health.ui.screens.register.RegisterStep
import fr.yjk.mobility.health.ui.screens.subscription.Subscription
import fr.yjk.mobility.health.ui.screens.subscription.partial.PaidSubscribe
import fr.yjk.mobility.health.ui.screens.workspace.Workspace
import kotlinx.serialization.Serializable


object Menu {
    @Serializable
    object Launch

    @Serializable
    object BeforeAuth

    @Serializable
    object Login


    @Serializable
    object Register

    @Serializable
    data class Verify(val next: String, val email: String)

    @Serializable
    object Workspace

    @Serializable
    object MakeDirectory

    @Serializable
    object Subscribe

    @Serializable
    object Paid
    @Serializable
    object Questions
}

@Composable
fun MainAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Any = Menu.Launch
) {
    val authUIState = LocalPreferences.current.authUIState.collectAsState().value
    val checkQuestion = LocalPreferences.current.isMakeDirectory.collectAsState().value

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = when (authUIState.isAuth) {
            is AuthState.Loading -> startDestination
            is AuthState.NotLoggedIn -> Menu.BeforeAuth /*Menu.Questions*/
            is AuthState.LoggedIn ->  if (checkQuestion) Menu.Questions else Menu.Workspace
        }
    ) {

        composable<Menu.Launch> {
            LaunchScreen()
        }
        composable<Menu.Questions> {
            MakeQuestion(
                onHome = {
                    navController.navigate(Menu.Workspace) {
                        popUpTo<Menu.Workspace> {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Menu.BeforeAuth> {
            BeforeAuthScreen(onLogin = {
                navController.navigate(Menu.Login)
            }, onRegister = {
                navController.navigate(Menu.Register)
            })
        }
        composable<Menu.Login> {
            LoginRequestScreen(onVerify = { email ->
                navController.navigate(Menu.Verify(next = "workspace", email = email))
            }, onBack = navController::navigateUp)
        }
        composable<Menu.Verify> {
            val next: Menu.Verify = it.toRoute()
            LoginOtpScreen(
                email = next.email,
                onHome = {
                    navController.navigate(if (next.next == "workspace") Menu.Workspace else Menu.MakeDirectory) {
                        popUpTo<Menu.Workspace> {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }, onBack = navController::navigateUp
            )
        }
        composable<Menu.Workspace> {
            Workspace(onSubscribe = {
                navController.navigate(Menu.Subscribe)
            })
        }
        composable<Menu.Register> {
            RegisterStep(onVerify = { email ->
                navController.navigate(Menu.Verify(next = "directory", email = email))
            }, onBack = navController::navigateUp)
        }
        composable<Menu.MakeDirectory> {
            MakeDirectory(onSubscribe = {
                navController.navigate(Menu.Subscribe)
            })
        }
        composable<Menu.Subscribe> {
            Subscription(onHome = {
                navController.navigate(Menu.Workspace) {
                    popUpTo<Menu.Workspace> {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            })
        }
        dialog<Menu.Paid>(dialogProperties = DialogProperties(dismissOnClickOutside = false)) {
            PaidSubscribe()
        }
    }
}
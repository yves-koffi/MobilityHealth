package fr.yjk.mobility.health.preferences

import fr.yjk.mobility.health.model.auth.CurrentUser
import kotlinx.serialization.json.Json


data class AuthUiState(
    private  val auth: String = "",
    val isAuth: AuthState = if (auth.isNotEmpty()) AuthState.LoggedIn else AuthState.NotLoggedIn,
    val currentUser: CurrentUser? = if (auth.isNotEmpty()) Json.decodeFromString<CurrentUser>(auth) else null
)


sealed class AuthState {
    data object Loading : AuthState() // hasLoggedIn = unknown
    data object LoggedIn : AuthState() // hasLoggedIn = true
    data object NotLoggedIn : AuthState() // hasLoggedIn = false
}
package fr.yjk.mobility.health.network.response

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class OtpResponse(
    val email: String
)

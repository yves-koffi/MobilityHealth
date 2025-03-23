package fr.yjk.mobility.health.model.auth

import androidx.compose.runtime.Immutable
import fr.yjk.mobility.health.model.Customer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class CurrentUser(
    @SerialName(value = "ref") val ref: String,
    val token: String,
    val customer: Customer
)
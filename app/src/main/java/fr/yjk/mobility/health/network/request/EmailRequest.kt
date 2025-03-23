package fr.yjk.mobility.health.network.request

import kotlinx.serialization.Serializable

@Serializable
data class EmailRequest(
    val email: String,
)

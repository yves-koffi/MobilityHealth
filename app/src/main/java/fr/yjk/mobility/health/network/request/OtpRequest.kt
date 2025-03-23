package fr.yjk.mobility.health.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OtpRequest(
    val otp: String,
    @SerialName("device_name") val deviceName: String,
)

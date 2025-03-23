package fr.yjk.mobility.health.model

import fr.yjk.mobility.health.model.converters.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import javax.annotation.concurrent.Immutable

@Immutable
@Serializable
data class Customer(
    val ref: String,
    val email: String? = null,
    val lastname: String,
    val firstname: String,
    val avatar: String? = null,
    @SerialName(value = "phone_number") val phoneNumber: String,
    @SerialName(value = "whatsapp_number") val whatsappNumber: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    @SerialName(value = "created_at") val createdAt: LocalDateTime,
    val card: InsuredCard
)


@Immutable
@Serializable
data class InsuredCard(
    @SerialName(value = "insured_number") val insuredNumber: String,
    @SerialName(value = "card_number") val cardNumber: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    @SerialName(value = "issue_date") val issueDate: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    @SerialName(value = "expiration_date") val expirationDate: LocalDateTime,
    val status: String
)
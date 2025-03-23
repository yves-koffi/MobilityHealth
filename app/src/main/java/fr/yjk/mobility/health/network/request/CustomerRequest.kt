package fr.yjk.mobility.health.network.request

import fr.yjk.mobility.health.model.converters.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class CustomerRequest(
    val lastname: String? = null,
    val firstname: String? = null,
    @Serializable(with = LocalDateTimeSerializer::class)
    @SerialName("birth_date") val birthday: LocalDateTime? = null,
    @SerialName("nationality_id") val nationalityId: String? = null,
    @SerialName("country_of_residence_id") val countryOfResidenceId: String? = null,
    @SerialName("phone_number") val phoneNumber: String? = null,
    @SerialName("whatsapp_number") val whatsappNumber: String? = null,
    val email: String? = null,
) {
    fun firstValidated(): Boolean {
        return !lastname.isNullOrEmpty() && !firstname.isNullOrEmpty() && nationalityId != null && countryOfResidenceId != null
    }

    fun lastValidated(): Boolean {
        return birthday != null && phoneNumber != null && whatsappNumber != null && email != null
    }
}

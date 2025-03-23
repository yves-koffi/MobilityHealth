package fr.yjk.mobility.health.network.request

import fr.yjk.mobility.health.model.QuestionGroup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionRequest(
    val questions: List<QuestionGroup>,
)

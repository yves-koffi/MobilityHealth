package fr.yjk.mobility.health.data.register

import fr.yjk.mobility.health.model.QuestionGroup
import fr.yjk.mobility.health.network.request.CustomerRequest
import fr.yjk.mobility.health.network.request.QuestionRequest
import fr.yjk.mobility.health.network.response.OtpResponse
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    suspend fun register(request: CustomerRequest): Flow<OtpResponse>
    suspend fun findQuestions(): Flow<List<QuestionGroup>>
    suspend fun saveAnswers(request: QuestionRequest): Flow<OtpResponse>
}
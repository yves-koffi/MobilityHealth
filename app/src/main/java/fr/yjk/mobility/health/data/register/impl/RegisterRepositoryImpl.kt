package fr.yjk.mobility.health.data.register.impl

import fr.yjk.mobility.health.data.register.RegisterRepository
import fr.yjk.mobility.health.model.QuestionGroup
import fr.yjk.mobility.health.network.response.OtpResponse
import fr.yjk.mobility.health.network.ApiService
import fr.yjk.mobility.health.network.request.CustomerRequest
import fr.yjk.mobility.health.network.request.QuestionRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : RegisterRepository {
    override suspend fun register(request: CustomerRequest): Flow<OtpResponse> =flow{
        emit(apiService.register(request=request))
    }

    override suspend fun findQuestions(): Flow<List<QuestionGroup>> =flow{
        emit(apiService.findQuestions())
    }

    override suspend fun saveAnswers(request: QuestionRequest): Flow<OtpResponse> =flow{
        emit(apiService.saveAnswers(request=request))
    }

}
package fr.yjk.mobility.health.network

import fr.yjk.mobility.health.model.QuestionGroup
import fr.yjk.mobility.health.network.request.CustomerRequest
import fr.yjk.mobility.health.network.request.QuestionRequest
import fr.yjk.mobility.health.network.response.OtpResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RegisteredService {

    @POST("api/customer/register")
    suspend fun register(
        @Body request: CustomerRequest
    ): OtpResponse


    @GET("api/auth/register/find/questions")
    suspend fun findQuestions(): List<QuestionGroup>

    @POST("api/auth/register/save/answers")
    suspend fun saveAnswers(
        @Body request: QuestionRequest
    ): OtpResponse
}
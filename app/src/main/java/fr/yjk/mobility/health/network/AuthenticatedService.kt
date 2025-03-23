package fr.yjk.mobility.health.network

import fr.yjk.mobility.health.model.auth.CurrentUser
import fr.yjk.mobility.health.network.request.EmailRequest
import fr.yjk.mobility.health.network.request.OtpRequest
import fr.yjk.mobility.health.network.response.OtpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticatedService {

    @POST("api/generate/otp")
    suspend fun generateOtp(
        @Body request: EmailRequest
    ): OtpResponse

    @POST("api/login/with/otp")
    suspend fun login(
        @Body request: OtpRequest
    ): CurrentUser
}
package fr.yjk.mobility.health.data.login

import fr.yjk.mobility.health.model.auth.CurrentUser
import fr.yjk.mobility.health.network.request.EmailRequest
import fr.yjk.mobility.health.network.request.OtpRequest
import fr.yjk.mobility.health.network.response.OtpResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun generateOtp(request: EmailRequest): Flow<OtpResponse>
    suspend fun login(request: OtpRequest): Flow<CurrentUser>
}
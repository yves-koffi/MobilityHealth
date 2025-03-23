package fr.yjk.mobility.health.data.login.impl

import fr.yjk.mobility.health.data.login.LoginRepository
import fr.yjk.mobility.health.model.auth.CurrentUser
import fr.yjk.mobility.health.network.response.OtpResponse
import fr.yjk.mobility.health.network.ApiService
import fr.yjk.mobility.health.network.request.EmailRequest
import fr.yjk.mobility.health.network.request.OtpRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : LoginRepository {
    override suspend fun generateOtp(request: EmailRequest): Flow<OtpResponse> = flow {
        emit(apiService.generateOtp(request = request))
    }

    override suspend fun login(request: OtpRequest): Flow<CurrentUser> = flow {
        emit(apiService.login(request = request))
    }

}
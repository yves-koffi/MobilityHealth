package fr.yjk.mobility.health.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.yjk.mobility.health.utils.Feedback
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.yjk.mobility.health.data.login.LoginRepository
import fr.yjk.mobility.health.model.auth.CurrentUser
import fr.yjk.mobility.health.network.UiResult
import fr.yjk.mobility.health.network.request.EmailRequest
import fr.yjk.mobility.health.network.response.OtpResponse
import fr.yjk.mobility.health.network.config.SessionKeys
import fr.yjk.mobility.health.network.request.OtpRequest
import fr.yjk.mobility.health.utils.extensions.toFailure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val sessionKeys: SessionKeys
) : ViewModel() {

    fun generateOtp(
        data: EmailRequest, on: Feedback<UiResult<OtpResponse>> = null
    ) {
        on?.invoke(UiResult.Loading)
        viewModelScope.launch {
            loginRepository.generateOtp(request = data).flowOn(Dispatchers.IO).catch { e ->
                on?.invoke(UiResult.Error(e.toFailure()))
            }.collect {
                on?.invoke(UiResult.Success(it))
            }
        }
    }

    fun login(
        data: OtpRequest, on: Feedback<UiResult<CurrentUser>> = null
    ) {
        on?.invoke(UiResult.Loading)
        viewModelScope.launch {
            loginRepository.login(request = data).flowOn(Dispatchers.IO).catch { e ->
                on?.invoke(UiResult.Error(e.toFailure()))
            }.collect {
                on?.invoke(UiResult.Success(it))
            }
        }
    }

}